package com.example.inventoryApp.model;

import java.util.ArrayList;

public class Asset {

    private String assetName;
    private String categoryName;
    private ArrayList<AssetItem> items = new ArrayList<>();
    private int amount;

    public Asset(String name) {
        this.assetName = name;
        this.categoryName = "Active";
        this.amount = 0;
    }
}
