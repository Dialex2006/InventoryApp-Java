package com.example.inventoryApp.model;

import javax.persistence.*;

@Entity
@Table(name = "AssetCategories")
public class AssetCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long categoryId;

    @Column(name = "CategoryName")
    private String categoryName;
}
