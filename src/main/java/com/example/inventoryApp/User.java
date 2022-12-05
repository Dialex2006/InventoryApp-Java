
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
    private ArrayList<Integer> assetsList = new ArrayList<Integer>();

    
    public User (String name) {
        idCounter++;
        this.userId = idCounter;
        this.name = name;
        this.status = "Active";
        this.location = "Tampere";
        this.email = name+"@company.com";
        this.startDate = new Date();
        
    }
    
    public void addAssetItem (int unitId) {
        this.assetsList.add(unitId); 
    }
    
    
    public int getUserId (String name) {
        return this.userId;     
    }
    
    
    
    
}
