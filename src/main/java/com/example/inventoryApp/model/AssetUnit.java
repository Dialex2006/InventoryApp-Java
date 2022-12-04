package com.example.inventoryApp.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "AssetCategories")
public class AssetUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long unitId;

    /* TODO: should be fk? */
    @Column(name = "OwnerId")
    private long ownerId;

    /* TODO: why serial number is text? Is it UID? */
    @Column(name = "SerialNumber")
    private String serialNumber;

    @Column(name = "PurchaseDate")
    private Date purchaseDate;

    /* TODO: enable proper foreign key supplier
     * @Column(name = "SupplierId")
     * private long supplierId;
     * ...
     **/

    public long getUnitId () {
        return unitId;
    }
    public void setOwnerId (long newOwnerId) {
        ownerId = newOwnerId;
    }
}
