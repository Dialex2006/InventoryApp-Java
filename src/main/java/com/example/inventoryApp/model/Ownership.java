package com.example.inventoryApp.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Ownership")
public class Ownership {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ownershipId;

    @Column(name = "OwnershipDate")
    private Date ownershipDate;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @Column(name = "UserId")
    private long userId;

    @OneToOne(targetEntity = Asset.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "AssetId", referencedColumnName = "AssetId")
    @Column(name = "AssetId")
    private long assetId;
}
