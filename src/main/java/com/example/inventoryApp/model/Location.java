package com.example.inventoryApp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LocationId")
    private long locationId;

    @Column(name = "LocationName")
    private String locationName;

    @OneToMany(targetEntity = User.class, cascade = CascadeType.ALL)
    private List<User> users;
}
