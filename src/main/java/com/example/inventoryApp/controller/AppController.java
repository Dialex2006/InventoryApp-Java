package com.example.inventoryApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class AppController {

    @GetMapping("/")
    public Map<String, Object> getIndex() {
        return Map.of("status", "ok", "error", "");
    }

    @GetMapping("*")
    public Map<String, Object> redirectToIndex() {
        return Map.of("status", "ok", "error", "");
    }
}
