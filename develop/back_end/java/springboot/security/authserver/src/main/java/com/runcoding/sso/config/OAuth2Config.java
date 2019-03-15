package com.runcoding.sso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;

/**
 * @author runcoding
 */
@Configuration
/**开启授权服务器*/
@EnableAuthorizationServer
public  class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private Environment env;

	@Value("classpath:sql/schema.sql")
	private Resource schemaScript;

	@Value("classpath:sql/data.sql")
	private Resource dataScript;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.pass"));
		return dataSource;
	}

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory(
                new ClassPathResource("keystore.jks"), "foobar".toCharArray())
                .getKeyPair("test");
        converter.setKeyPair(keyPair);
        return converter;
    }

	@Bean
	public TokenStore tokenStore() {
		// 使用JdbcTokenStore把token存储到数据库中，RedisTokenStore的使用方法也类似
		return new JdbcTokenStore(dataSource());
	}


	/**
	 * http://www.iocoder.cn/Spring-Security/OAuth2-learning/
	 * 生产使用数据库管理
	 * 1. 客户端密码模式：
	 *  curl -X POST --user acme:acmesecret http://localhost:8080/oauth/token -H "accept: application/json" -H "content-type: application/x-www-form-urlencoded" -d "grant_type=password&username=runcoding&password=runcoding&scope=openid"
	 *  -- 刷新token
	 *  curl -i -X POST -u 'acme:acmesecret'  http://localhost:8080/oauth/token -H "accept: application/json" -d 'grant_type=refresh_token&refresh_token=xxxx'
	 *
	 *
	 * 2. 客户端模式：
	 * curl -X POST "http://localhost:8080/oauth/token" --user acme:acmesecret -d "grant_type=client_credentials&scope=read_contacts"
	 *
	 *
	 * */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		/**
		 * 设置第二个client
		 * scopes.and().withClient()
		 * // 设置OAuth2的client信息也使用数据库存储和读取
		 * **/
         //clients.jdbc(dataSource());

        clients.inMemory()
                .withClient("acme")
                .secret("acmesecret")
                .authorizedGrantTypes("authorization_code", "refresh_token",
                        "password").scopes("openid");
	}


	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints)
			throws Exception {
        endpoints.authenticationManager(authenticationManager).accessTokenConverter(
                jwtAccessTokenConverter());

	}

/*	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		return defaultTokenServices;
	}*/

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()");
		oauthServer.checkTokenAccess("isAuthenticated()");
		//oauthServer.allowFormAuthenticationForClients();
	}

	@Bean
	public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
		final DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(dataSource);
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(schemaScript);
		populator.addScript(dataScript);
		initializer.setDatabasePopulator(populator);
		return initializer;
	}

	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}


}