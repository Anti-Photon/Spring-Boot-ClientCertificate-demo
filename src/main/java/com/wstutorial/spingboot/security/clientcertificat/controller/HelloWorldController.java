package com.wstutorial.spingboot.security.clientcertificat.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping("/")
    public String homePage(){
        System.out.println("homepage");
        return "HomePage";
    }

    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello");
        return "Hello World";
    }

    @RequestMapping("/protected")
    public String protectedHello(){
        return "Hello World, i was protected";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "Hello World from admin";
    }

    @RequestMapping("/{resource}")
    public String resource(@PathVariable("resource") String resource){
        return "Hello World from resource: " + resource;
    }

}
