package com.runcoding.sso.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
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

	/***
	 * http://blog.didispace.com/xjf-spring-security-3/
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {

		    http
				.authorizeRequests()
					/**放行请求配置*/
					.antMatchers("/dashboard/user").permitAll()
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
					 ;
			//.and().csrf().csrfTokenRepository(csrfTokenRepository()).and()
			//.addFilterAfter(csrfHeaderFilter(), CsrfFilter.class)
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		///,/favicon.ico,/index.html,/home.html,/dashboard.html,/js/**,/css/**,/webjars/**
		web.ignoring().antMatchers("/resources/**");
				//.antMatchers("////","/js/**", "/css/**", "/images/**", "/**/favicon.ico","/index.html","/home.html","/dashboard.html","/js/**","/webjars/**");
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