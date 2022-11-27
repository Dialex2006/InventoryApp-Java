
package com.example.inventoryApp;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    ArrayList<User> findAll();
    
    ArrayList<User> findByName(String name);
}


