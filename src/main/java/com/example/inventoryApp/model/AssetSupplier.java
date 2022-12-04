package com.example.inventoryApp.model;

import javax.persistence.*;

@Entity
@Table(name = "AssetCategories")
public class AssetSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long supplierId;

    @Column(name = "SupplierName")
    private String supplierName;
}
