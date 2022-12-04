
package com.example.inventoryApp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AssetController {
    
    @Autowired
    private AssetService assetService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AssetItemService assetItemService;
    
    @GetMapping("/assets")
    public String getAssets(Model model) {
        //List<Asset> assetsList = assetService.getAssetsList();
        List<String> assetsList = assetService.getAssetsList();
        //System.out.println("assetsList: " + assetsList.get(0).toString());
        model.addAttribute("assetsList", assetsList);
        return "assetsPage";  // here comes the name of HTML page in templates
        
    }
    
    @PostMapping("/assets")
    public String addAssets(@RequestParam String assetName, String serialNumber, String supplier) {
        assetItemService.addAssetItem(new AssetItem(assetName, serialNumber, supplier));
        return "redirect:/assets";
        
    }
    
    @GetMapping("/assets/{asset}")
    public String getAssetInfo (@PathVariable String asset, Model model) {
        ArrayList<AssetItem> assetItems = assetItemService.getAllByName(asset);
        model.addAttribute("assetItems", assetItems);
        //model.addAttribute("questsList", questService.getQuests());
        return "assetItemsPage";
    }
    
    
    @GetMapping("/assets/{asset}/{serialNumber}")
    public String getUnitInfo (@PathVariable String asset, @PathVariable String serialNumber, Model model) {
        AssetItem assetItem = assetItemService.findItemBySerialNumber(serialNumber);
        model.addAttribute("assetItem", assetItem);
        
        List<User> usersList = userService.getUsers();
        System.out.println("usersList: " + usersList);
        model.addAttribute("usersList", usersList);
        
        return "assetUnitPage";
    }
    
    
    @PostMapping("/assign")
    public String AssignAssets(@RequestParam String userName, @RequestParam String serialNumber) {
        AssetItem assetItem = assetItemService.findItemBySerialNumber(serialNumber);
        User user = userService.findUserByName(userName);
        assetItem.setOwnerId(user.getUserId());
        //assetItemService.setAssetItem(new AssetItem(assetName, serialNumber, supplier));
        return "redirect:/assets";
        
    }
    
    
}
