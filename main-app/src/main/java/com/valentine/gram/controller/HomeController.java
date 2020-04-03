package com.valentine.gram.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @GetMapping("/")
    public String string() {
        return "HELLO MYGRAM SERVER";
    }
}
