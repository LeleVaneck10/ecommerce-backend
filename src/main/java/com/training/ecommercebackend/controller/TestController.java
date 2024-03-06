package com.training.ecommercebackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class TestController {

    @GetMapping
    public String sayHello(){
        return " hello everybody";
    }
}