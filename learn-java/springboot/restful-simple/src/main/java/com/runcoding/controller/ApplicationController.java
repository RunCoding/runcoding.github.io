package com.runcoding.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApplicationController {


    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ApiOperation(value="进入系统应用主页", notes="默认跳转到swagger-ui")
    public String app(){
        return "redirect:/swagger-ui.html";
    }

    @RequestMapping(value = "/user/add",method = RequestMethod.POST)
    @ResponseBody
    public User addUser(@RequestBody User user){
        System.out.println("post="+JSON.toJSONString(user));
        return user;
    }

    @RequestMapping(value = "/user/put",method = RequestMethod.PUT)
    @ResponseBody
    public User putUser(@RequestBody User user){
        System.out.println("put="+JSON.toJSONString(user));
        return user;
    }



}

class User{
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
