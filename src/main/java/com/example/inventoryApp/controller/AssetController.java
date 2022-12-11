package com.example.inventoryApp.controller;

import com.example.inventoryApp.model.AssetItem;
import com.example.inventoryApp.model.User;
import com.example.inventoryApp.service.AssetItemService;
import com.example.inventoryApp.service.AssetService;
import com.example.inventoryApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
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
        List<String> assetsList = assetService.getAssetsNamesList();
        System.out.print("List of assets: " + assetsList);
        ArrayList<AssetItem> assetItems = new ArrayList<>();
        try {
            // check how many of each category we have now
            Map<String, Integer> map = new HashMap<>();
            for (String assetName : assetsList) {
                map.put(assetName, assetItemService.getAllByName(assetName).size());
                assetItems = assetItemService.getAllByName(assetName);
                //assetItems.add(assetItemService.getAllByName(assetName));
            }
            assetItems = assetService.getAssetsList();
            model.addAttribute("map", map);
        } catch (Exception e) {
            status = "error";
            error = e.getCause().getMessage();
        }
        return Map.of("status", status, "error", error, "data", Map.of("assets", assetItems));
    }

    @PostMapping("/assets/add")
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

    @GetMapping("/assets/name/{asset}")
    public Map<String, Object> getAssetInfo(@PathVariable String asset, Model model) {
        String status = "ok";
        String error = "";
        ArrayList<AssetItem> namedAssetItems = new ArrayList<>();
        try {
            namedAssetItems = assetItemService.getAllByName(asset);
            model.addAttribute("assetItems", namedAssetItems);
        } catch (Exception e) {
            status = "error";
            error = e.getCause().getMessage();
        }
        return Map.of("status", status, "error", error, "data", Map.of("assets", namedAssetItems));
    }

    @GetMapping("/assets/number/{serialNumber}")
    public Map<String, Object> getUnitInfo(@PathVariable String serialNumber, Model model) {
        String status = "ok";
        String error = "";
        AssetItem assetItem = new AssetItem();
        try {
            assetItem = assetItemService.findItemBySerialNumber(serialNumber);
            model.addAttribute("assetItem", assetItem);
            List<User> usersList = userService.getUsers();
            model.addAttribute("usersList", usersList);
        } catch (Exception e) {
            status = "error";
            error = e.getCause().getMessage();
        }
        return Map.of("status", status, "error", error, "data", Map.of("assetItem", assetItem));
    }

    @PostMapping("/assets/assign")
    public Map<String, Object> AssignAssets(@RequestParam String userName, @RequestParam int id) {
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
