package com.runcoding;

import com.google.common.collect.Maps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import java.util.HashMap;

/**
 * Spring Boot 应用启动类
 *
 * Created by bysocket on 16/4/26.
 */
@SpringBootApplication
@ServletComponentScan
public class Application {

    public Application() {
        System.out.println("init");
    }

    public static void main(String[] args) {
        HashMap<String, Application> hashMap = Maps.newHashMap();
        Application application = hashMap.getOrDefault("a", new Application());
        System.out.println(application);

        // 程序启动入口
        // 启动嵌入式的 Tomcat(jetty) 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(Application.class,args);
    }
}
