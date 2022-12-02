
package com.example.inventoryApp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Asset {
    
    private String assetName;
    private String categoryName;
    private ArrayList<AssetItem> items = new ArrayList<AssetItem>();
    private int amount;

    
    public Asset (String name) {
        this.assetName = name;
        this.categoryName = "Active";
        this.amount = 0;

        
    }
    
}
