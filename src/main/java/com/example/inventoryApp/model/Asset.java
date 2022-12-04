package com.example.inventoryApp.model;

import javax.persistence.*;

@Entity
@Table(name = "Assets")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OneToOne(targetEntity = Ownership.class, cascade = CascadeType.ALL)
    private long assetId;

    @Column(name = "AssetName")
    private String assetName;

    @Column(name = "CurrentStock")
    private int currentStock;

    /* TODO: enable proper foreign key category
     * @Column(name = "CategoryId")
     * private long categoryId;
     * ...
     **/

    /* TODO: enable proper foreign key asset unit
     * @Column(name = "AssetUnitId")
     * private long assetUnitId;
     * ...
     **/

    public Asset(String name) {
        assetName = name;
    }

    String getAssetName() {
        return assetName;
    }
}
