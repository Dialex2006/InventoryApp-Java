
package com.example.inventoryApp;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data


public class AssetItem extends AbstractPersistable<Integer> {
    private static int idCounter=0;
    private int unitId;
    private String itemName;
    private String serialNumber;
    private Date purchaseDate;
    private String supplier;
    private int ownerId;

    
    public AssetItem () {
        idCounter++;
        this.unitId = idCounter;
        this.itemName = " ";
        this.serialNumber = " ";
        this.purchaseDate = new Date();
        this.supplier = " ";
        
    }
    
    
    
    public AssetItem (String name, String serial, String supplier) {
        idCounter++;
        this.unitId = idCounter;
        this.itemName = name;
        this.serialNumber = serial;
        this.purchaseDate = new Date();
        this.supplier = supplier;
        this.ownerId = 0;
        
           
    }
    
    
    public int getUnitId () {
        return this.unitId;
    }
    
    
    public void setOwnerId (int ownerId) {
        this.ownerId = ownerId;
    }
    
}
