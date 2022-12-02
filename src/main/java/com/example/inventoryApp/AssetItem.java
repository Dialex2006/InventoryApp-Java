
package com.example.inventoryApp;

import java.util.Date;
import java.util.List;


public class AssetItem {
    
    private String itemName;
    private String serialNumber;
    private Date purchaseDate;
    private String supplier;

    
    public AssetItem () {
        this.itemName = " ";
        this.serialNumber = " ";
        this.purchaseDate = new Date();
        this.supplier = " ";
        
    }
    
    
    
    public AssetItem (String name, String serial, String supplier) {
        this.itemName = name;
        this.serialNumber = "serial";
        this.purchaseDate = new Date();
        this.supplier = supplier;
        

        
    }
    
}
