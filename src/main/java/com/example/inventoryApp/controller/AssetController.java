package com.example.inventoryApp.controller;

import com.example.inventoryApp.model.AssetUnit;
import com.example.inventoryApp.model.User;
import com.example.inventoryApp.service.AssetService;
import com.example.inventoryApp.service.AssetUnitService;
import com.example.inventoryApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class AssetController {

    @Autowired
    private AssetService assetService;

    @Autowired
    private UserService userService;

    @Autowired
    private AssetUnitService assetUnitService;

    @GetMapping("/assets")
    public String getAssets(Model model) {
        List<List> listOfMixedTypes = new ArrayList<List>();
        List<String> assetsList = assetService.getAssetsList();
        ArrayList<Integer> amounts = new ArrayList<Integer>();

        //checking how many of each category we have now
        Map<String, Integer> map = new HashMap<>();
        for (String assetName : assetsList) {
            map.put(assetName, assetUnitService.getAllByName(assetName).size());
            ArrayList<AssetUnit> assetItems = assetUnitService.getAllByName(assetName);
            amounts.add(assetItems.size());
        }

        //model.addAttribute("assetsList", assetsList);
        model.addAttribute("map", map);
        return "assetsPage";  // here comes the name of HTML page in templates

    }

    @PostMapping("/assets")
    public String addAssets(@RequestParam String assetName, String serialNumber, String supplier) {
//        assetUnitService.addAssetItem(new AssetUnit(assetName, serialNumber, supplier));
        return "redirect:/assets";

    }

    @GetMapping("/assets/{asset}")
    public String getAssetInfo(@PathVariable String asset, Model model) {
        ArrayList<AssetUnit> assetItems = assetUnitService.getAllByName(asset);
        model.addAttribute("assetItems", assetItems);
        //model.addAttribute("questsList", questService.getQuests());
        return "assetItemsPage";
    }

    @GetMapping("/assets/{asset}/{serialNumber}")
    public String getUnitInfo(@PathVariable String asset, @PathVariable String serialNumber, Model model) {
        AssetUnit assetItem = assetUnitService.findItemBySerialNumber(serialNumber);
        model.addAttribute("assetItem", assetItem);

        List<User> usersList = userService.getUsers();
        System.out.println("usersList: " + usersList);
        model.addAttribute("usersList", usersList);

        return "assetUnitPage";
    }

    @PostMapping("/assign")
    public String AssignAssets(@RequestParam String userName, @RequestParam int id, @RequestParam String serialNumber) {
        System.out.println("Assets's ID: " + id);
        System.out.println("Serial: " + serialNumber);
        //System.out.println("Assets's ID: " + id);
        AssetUnit assetUnit = assetUnitService.findItemById(id);
        //AssetUnit assetItem = assetItemService.findItemBySerialNumber(serialNumber);
        User user = userService.findUserByName(userName);
        assetUnit.setOwnerId(user.getId());

        assetUnitService.setAssetUnit(assetUnit);
        return "redirect:/assets";
    }
}
