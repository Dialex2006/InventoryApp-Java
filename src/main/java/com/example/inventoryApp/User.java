
package com.example.inventoryApp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@AllArgsConstructor // constructor taking all args
@NoArgsConstructor // constructor without args
@Data //for getters and setters

public class User extends AbstractPersistable<Long> {
    private static int idCounter=0;
    private int userId;
    private String name;
    private String status;
    private String location;
    private String email;
    private Date startDate;
    //private ArrayList<AssetItem> assets = new ArrayList<AssetItem>();
    private ArrayList<Integer> assetsList = new ArrayList<Integer>();

    
    public User (String name) {
        idCounter++;
        this.userId = idCounter;
        this.name = name;
        this.status = "Active";
        this.location = "Tampere";
        this.email = name+"@company.com";
        this.startDate = new Date();
        //AssetItem newItem = new AssetItem("Name1", "Serial1", "Supplier1");
        //System.out.println(newItem.getUnitId());
        //AssetItem newItem2 = new AssetItem("Name2", "Serial2", "Supplier2");
        //System.out.println(newItem2.getUnitId());
        //this.assets.add(newItem);
        //this.assetsList.add(newItem.getUnitId());
        
    }
    
    public void addAssetItem (int unitId) {
        this.assetsList.add(unitId);
        
    }
    
    
     public int getId (String name) {
        return this.userId;
        
    }
    
    
    public int getUserId (String name) {
        return this.userId;
        
    }
    
    
    
    
}
