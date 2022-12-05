package com.example.inventoryApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AppController {

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("*")
    public String redirectToIndex() {
        return "redirect:/";
    }
}
