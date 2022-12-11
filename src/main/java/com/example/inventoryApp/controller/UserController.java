
package com.example.inventoryApp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.inventoryApp.model.AssetItem;
import com.example.inventoryApp.service.AssetItemService;
import com.example.inventoryApp.model.User;
import com.example.inventoryApp.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AssetItemService assetItemService;

    @Autowired
    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping(value = "/users")
    public Map<String, Object> getUsers(Model model) {
        String status = "ok";
        String error = "";
        List<User> usersList = new ArrayList<>();
        try {
            usersList = userService.getUsers();
            model.addAttribute("usersList", usersList);
        } catch (Exception e) {
            status = "error";
            error = e.getCause().getMessage();
        }
        return Map.of("status", status, "error", error, "data", Map.of("users", usersList));
    }

    @PostMapping("/users/add")
    public Map<String, Object> addUsers(@RequestBody String userData) {
        String status = "ok";
        String error = "";
        try {
            // read JSON data from file using fileObj and map it using ObjectMapper and TypeReference classes
            Map<String, String> userMap = mapper.readValue(
                    userData, new TypeReference<>() {
                    });
            userService.addUser(userMap.get("username"));
        } catch (Exception e) {
            status = "error";
            error = e.getCause().getMessage();
        }

        return Map.of("status", status, "error", error);
    }

    @GetMapping("/users/{userName}")
    public Map<String, Object> getUserInfo(@PathVariable String userName, Model model) {
        String status = "ok";
        String error = "";
        User user = new User();
        try {
            user = userService.findUserByName(userName);
            model.addAttribute("user", user);
            ArrayList<AssetItem> items = new ArrayList<>();
            for (int id : user.getAssetsList()) {
                items.add(assetItemService.findItemById(id));
            }
            model.addAttribute("items", items);
        } catch (Exception e) {
            status = "error";
            error = e.getCause().getMessage();
        }
        return Map.of("status", status, "error", error, "data", Map.of("user", user));
    }
}
