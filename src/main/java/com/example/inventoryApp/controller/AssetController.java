package com.example.inventoryApp.controller;

import com.example.inventoryApp.model.AssetItem;
import com.example.inventoryApp.model.User;
import com.example.inventoryApp.service.AssetItemService;
import com.example.inventoryApp.service.AssetService;
import com.example.inventoryApp.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Autowired
    private final ObjectMapper mapper = new ObjectMapper();

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
    public Map<String, Object> addAssets(@RequestBody String assetData) {
        String status = "ok";
        String error = "";

        System.out.println("asset data: " + assetData);

        try {
            // read JSON data from file using fileObj and map it using ObjectMapper and TypeReference classes
            Map<String, Object> assetMap = mapper.readValue(assetData, new TypeReference<>() {
            });
            Map<String, String> asset = (Map<String, String>) assetMap.get("asset");
            System.out.println("asset map: " + asset);
            assetItemService.addAssetItem(new AssetItem(asset.get("assetName"), asset.get("serialNumber"), asset.get("supplier")));
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
    public Map<String, Object> AssignAssets(@RequestBody String assignData) {
        String status = "ok";
        String error = "";
        try {
            // read JSON data from file using fileObj and map it using ObjectMapper and TypeReference classes
            Map<String, String> assignMap = mapper.readValue(assignData, new TypeReference<>() {
            });
            int id = Integer.parseInt(assignMap.get("id"));
            String username = assignMap.get("username");
            AssetItem assetItem = assetItemService.findItemById(id);
            User user = userService.findUserByName(username);
            assetItem.setOwnerId(user.getUserId());
            assetItemService.setAssetItem(assetItem);
            userService.addAssetItemToUser(username, id);
        } catch (Exception e) {
            status = "error";
            error = e.getCause().getMessage();
        }
        return Map.of("status", status, "error", error);
    }
}
