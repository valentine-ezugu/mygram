package com.valentine.gram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller("/api")
public class TestController {

    @GetMapping("/test")
    public String getHelloWorld() {
        return "Hello World";
    }

}
