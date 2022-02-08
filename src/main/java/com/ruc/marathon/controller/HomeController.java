package com.ruc.marathon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String login() {
        return "login"; //view
    }
    @GetMapping("/register")
    public String register() {
        return "register"; //view
    }
}
