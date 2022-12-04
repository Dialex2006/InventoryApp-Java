package com.example.inventoryApp.service;

import com.example.inventoryApp.model.User;
import com.example.inventoryApp.repository.AssetUnitRepository;
import com.example.inventoryApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssetUnitService assetUnitService;

    @Autowired
    private AssetUnitRepository assetUnitRepository;

    public ArrayList<User> getUsers() {
        //return this.users;
        return userRepository.findAll();
    }

    public void addUser(String userName) {
        User h = new User(userName);
        //this.heroes.add(h);
        userRepository.save(h);
    }
    
    public User findUserByName (String userName) {
        
//        for (Hero h : this.heroes) {
//            if (h.getName().equals(heroName)) {
//                return h;
//            }
//        }
//        
//        return heroes.get(0);
        return userRepository.findByName(userName).get(0);
    }
}
