
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
    private String name;
    private String status;
    private String location;
    private String email;
    private Date startDate;
    private ArrayList<AssetItem> assets = new ArrayList<AssetItem>();

    
    public User (String name) {
        this.name = name;
        this.status = "Active";
        this.location = "Tampere";
        this.email = name+"@company.com";
        this.startDate = new Date();
        AssetItem newItem = new AssetItem("Name1", "Serial1", "Supplier1");
        this.assets.add(newItem);
        
        
    }
    
    
}
