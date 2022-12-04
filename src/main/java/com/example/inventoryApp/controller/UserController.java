package com.example.inventoryApp.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.inventoryApp.model.AssetUnit;
import com.example.inventoryApp.model.User;
import com.example.inventoryApp.service.AssetUnitService;
import com.example.inventoryApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AssetUnitService assetUnitService;

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> usersList = userService.getUsers();
        model.addAttribute("usersList", usersList);
        return "usersPage";  // here comes the name of HTML page in templates
    }

    @PostMapping("/users")
    public String addUsers(@RequestParam String userName) {
        userService.addUser(userName);
        return "redirect:/users";
    }

    @GetMapping("/users/{userName}")
    public String getUserInfo(@PathVariable String userName, Model model) {
        User user = userService.findUserByName(userName);
        model.addAttribute("user", user);
        ArrayList<AssetUnit> items = new ArrayList<>();
//        for (int id : user.getAssetsList()) {
//            System.out.println("ID: " + id);
//            System.out.println("Items: " + assetUnitService.getAllItems());
//            System.out.println("Item: " + assetUnitService.findItemById(id));
//            items.add(assetUnitService.findItemById(id));
//        }
        model.addAttribute("items", items);
        System.out.println(model);
        //model.addAttribute("questsList", questService.getQuests());
        return "userPage";
    }
}