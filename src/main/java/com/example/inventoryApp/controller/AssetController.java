package com.example.inventoryApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.inventoryApp.model.AssetItem;
import com.example.inventoryApp.model.User;
import com.example.inventoryApp.service.AssetItemService;
import com.example.inventoryApp.service.AssetService;
import com.example.inventoryApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
public class AssetController {

    @Autowired
    private AssetService assetService;

    @Autowired
    private UserService userService;

    @Autowired
    private AssetItemService assetItemService;

    @GetMapping("/assets")
    public Map<String, Object> getAssets(Model model) {
        String status = "ok";
        String error = "";
        List<String> assetsList = assetService.getAssetsList();
        try {
            // check how many of each category we have now
            Map<String, Integer> map = new HashMap<>();
            for (String assetName : assetsList) {
                map.put(assetName, assetItemService.getAllByName(assetName).size());
                ArrayList<AssetItem> assetItems = assetItemService.getAllByName(assetName);
            }
            model.addAttribute("map", map);
        } catch (Exception e) {
            status = "error";
            error = e.getCause().getMessage();
        }
        return Map.of("status", status, "error", error, "data", assetsList);
    }

    @PostMapping("/assets")
    public Map<String, Object> addAssets(@RequestParam String assetName, String serialNumber, String supplier) {
        String status = "ok";
        String error = "";
        try {
            assetItemService.addAssetItem(new AssetItem(assetName, serialNumber, supplier));
        } catch (Exception e) {
            status = "error";
            error = e.getCause().getMessage();
        }
        return Map.of("status", status, "error", error);
    }

    @GetMapping("/assets/{asset}")
    public Map<String, Object> getAssetInfo(@PathVariable String asset, Model model) {
        String status = "ok";
        String error = "";
        ArrayList<AssetItem> assetItems = new ArrayList<>();
        try {
            assetItems = assetItemService.getAllByName(asset);
            model.addAttribute("assetItems", assetItems);
        } catch (Exception e) {
            status = "error";
            error = e.getCause().getMessage();
        }
        return Map.of("status", status, "error", error, "data", assetItems);
    }

    @GetMapping("/assets/{asset}/{serialNumber}")
    public Map<String, Object> getUnitInfo(@PathVariable String asset, @PathVariable String serialNumber, Model model) {
        String status = "ok";
        String error = "";
        List<User> usersList = new ArrayList<>();
        try {
            AssetItem assetItem = assetItemService.findItemBySerialNumber(serialNumber);
            model.addAttribute("assetItem", assetItem);
            usersList = userService.getUsers();
            model.addAttribute("usersList", usersList);
        } catch (Exception e) {
            status = "error";
            error = e.getCause().getMessage();
        }
        return Map.of("status", status, "error", error, "data", usersList);
    }

    @PostMapping("/assign")
    public Map<String, Object> AssignAssets(@RequestParam String userName, @RequestParam int id, @RequestParam String serialNumber) {
        String status = "ok";
        String error = "";
        try {
            AssetItem assetItem = assetItemService.findItemById(id);
            User user = userService.findUserByName(userName);
            assetItem.setOwnerId(user.getUserId());
            assetItemService.setAssetItem(assetItem);
            userService.addAssetItemToUser(userName, id);
        } catch (Exception e) {
            status = "error";
            error = e.getCause().getMessage();
        }
        return Map.of("status", status, "error", error);
    }
}
