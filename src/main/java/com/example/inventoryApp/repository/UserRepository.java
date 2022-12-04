package com.example.inventoryApp.repository;

import java.util.ArrayList;

import com.example.inventoryApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    ArrayList<User> findAll();

    ArrayList<User> findByName(String name);
}


