package com.runcoding.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

/**
 * @author xukai
 * @date 2019-03-14
 * @desc:
 */
@Controller
@RequestMapping("/dashboard")
public class SsoController {


    @RequestMapping("/login")
    public String login() {
        return "redirect:/#/";
    }

    @RequestMapping("/message")
    @ResponseBody
    public Map<String, Object> dashboardMessage() {
        return Collections.singletonMap("message", "Yay!");
    }

    @RequestMapping("/user")
    @ResponseBody
    public Principal user(Principal user) {
        return user;
    }


}
