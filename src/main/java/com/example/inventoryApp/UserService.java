
package com.example.inventoryApp;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AssetItemService assetItemService;
    
    @Autowired
    private AssetItemRepository assetItemRepository;
    
    public ArrayList<User> getUsers() {
        return this.userRepository.findAll();
            
    }
    
    public void addUser (String userName) {
        User h = new User (userName);
        this.userRepository.save(h);
    }
    
    public User findUserByName (String userName) {
        return this.userRepository.findByName(userName).get(0);
    }
    
    
    public void addAssetItemToUser(String userName, int unitId) {
        User user = this.findUserByName(userName);
        AssetItem assetItem = assetItemService.findItemById(unitId);
        user.addAssetItem(unitId);
        this.userRepository.save(user);
    }
    
    

}
