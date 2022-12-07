package com.example.inventoryApp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.inventoryApp.model.AssetItem;
import com.example.inventoryApp.service.AssetItemService;
import com.example.inventoryApp.model.User;
import com.example.inventoryApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AssetItemService assetItemService;


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

    @PostMapping("/users")
    public Map<String, Object> addUsers(@RequestParam String userName) {
        String status = "ok";
        String error = "";
        try {
            userService.addUser(userName);
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
        ArrayList<AssetItem> items = new ArrayList<>();
        try {
            User user = userService.findUserByName(userName);
            model.addAttribute("user", user);
            for (int id : user.getAssetsList()) {
                items.add(assetItemService.findItemById(id));
            }
            model.addAttribute("items", items);
        } catch (Exception e) {
            status = "error";
            error = e.getCause().getMessage();
        }
        return Map.of("status", status, "error", error, "data", Map.of("userItems", items));
    }
}
