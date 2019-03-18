package com.runcoding.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.security.Principal;

/**
 * @author xukai
 * @date 2019-03-14
 * @desc:
 */
@RestController
@SessionAttributes("authorizationRequest")
public class AuthorizationController extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/oauth/confirm_access").setViewName("authorize");
    }

    @Autowired
    private ConsumerTokenServices tokenServices;

    @RequestMapping(method = RequestMethod.POST, value = "api/access_token/revoke")
    public String revokeToken(@RequestParam("token") String token) {
        tokenServices.revokeToken(token);
        return token;
    }

    @GetMapping("/user/me")
    public Principal user(Principal principal) {
        return principal;
    }
}
