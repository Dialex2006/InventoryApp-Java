package com.example.inventoryApp;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
     
    @Autowired
    private UserService userService;
    
    @Autowired
    private AssetItemService assetItemService;

    
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
    public String getUserInfo (@PathVariable String userName, Model model) {
        User user = userService.findUserByName(userName);
        model.addAttribute("user", user);
        ArrayList<AssetItem> items = new ArrayList<AssetItem>();
        for (int id : user.getAssetsList()) {
            //System.out.println("ID: " + id);
            //System.out.println("Items: " + assetItemService.getAllItems());
            //System.out.println("Item: " + assetItemService.findItemById(id));
            items.add(assetItemService.findItemById(id));
        }
        model.addAttribute("items", items);
        System.out.println(model);

        return "userPage";
    
   
    
    
    }
    

}