package com.example.test.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    public String testApi() {
        return "Connexion réussie depuis Angular 🎉";
    }
}
