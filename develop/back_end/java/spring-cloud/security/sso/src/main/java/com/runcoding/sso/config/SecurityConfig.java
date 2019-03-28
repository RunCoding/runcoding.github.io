package com.runcoding.sso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author runcoding
 */
@Configuration
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAccessTokenConverter customAccessTokenConverter;

	/***
	 * http://blog.didispace.com/xjf-spring-security-3/
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {

		    http
				.authorizeRequests()
					/**放行请求配置*/
					.antMatchers("/dashboard/user","/oauth/login").permitAll()
					/**需要认证的地址配置*/
				    .antMatchers("/dashboard/**").authenticated()
					/**角色配置*/
					.antMatchers("/dashboard/userInfo").hasAuthority("ROLE_USER").anyRequest().permitAll()
				.and()
				.logout()
					.deleteCookies("OAUTH2SESSION")
					.invalidateHttpSession(false)
					.logoutUrl("/dashboard/logout")
					.logoutSuccessUrl("/")
					.permitAll()
			    .and()
					.rememberMe().tokenValiditySeconds(2419200)
			    .and()
				.csrf()
					.disable()
				.httpBasic()
					.disable()
					.addFilterAfter(new RewriteAccessDenyFilter(), ExceptionTranslationFilter.class)
				    .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
					 ;
			//.and().csrf().csrfTokenRepository(csrfTokenRepository()).and()
			//.addFilterAfter(csrfHeaderFilter(), CsrfFilter.class)
	}

	@Bean
	public JwtAuthenticationFilter authenticationTokenFilterBean(){
		return new JwtAuthenticationFilter();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Primary
	@Bean
	public RemoteTokenServices tokenServices() {
		final RemoteTokenServices tokenService = new RemoteTokenServices();
		tokenService.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
		tokenService.setClientId("fooClientIdPassword");
		tokenService.setClientSecret("secret");
		return tokenService;
	}

	private String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkNcERjHvpqxXCAoI2HEN\n" +
			"rZCbKeaEcE7KWiCx9LXAMHbOQra8gg3Cx5UZqkKSIWTLOy0yLf57LI6QoVWlC2UA\n" +
			"hOihUKJQ/Alj2MUyPutaHxY9nDeeZ6CN5xKf1oYDsyMuhr2wWYq0k8XMjLjYrAb4\n" +
			"e3jTnrpl/gsyAiOEZnGufNEx2nVuJX4EB15mI1tgW2fNMbTs2MtAsen05qaR99rh\n" +
			"Cxsc7ohnKKZ5P78lVz45g+ULhFJHAahrCV0tjIMFoyb+j3QqW9Tal6ZwOTome0UL\n" +
			"xhxhn6aeUU2Ws8x76v/BTdZKZJUIAfrwwxPjQFWWbfx4aM/DLnK7h0mf2ydKK7Dq\n" +
			"gwIDAQAB\n" +
			"-----END PUBLIC KEY-----";

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setAccessTokenConverter(customAccessTokenConverter);
		// converter.setSigningKey("secret");
		converter.setVerifierKey(publicKey);
		return converter;
	}

	@Bean
	public TokenStore tokenStore() {
		// 使用JdbcTokenStore把token存储到数据库中，RedisTokenStore的使用方法也类似
		//return new JdbcTokenStore(dataSource());
		return new JwtTokenStore(accessTokenConverter());
	}


	private Filter csrfHeaderFilter() {
		return new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request,
											HttpServletResponse response, FilterChain filterChain)
					throws ServletException, IOException {
				CsrfToken csrf = (CsrfToken) request
						.getAttribute(CsrfToken.class.getName());
				if (csrf != null) {
					Cookie cookie = new Cookie("XSRF-TOKEN",
							csrf.getToken());
					cookie.setPath("/");
					response.addCookie(cookie);
				}
				filterChain.doFilter(request, response);
			}
		};
	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}
}