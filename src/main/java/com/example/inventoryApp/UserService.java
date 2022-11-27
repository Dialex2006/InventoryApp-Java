
package com.example.inventoryApp;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public ArrayList<User> getUsers() {
        //return this.users;
        return this.userRepository.findAll();
        
        
    }
    
    public void addUser (String userName) {
        User h = new User (userName);
        //this.heroes.add(h);
        this.userRepository.save(h);
    }
    
}
