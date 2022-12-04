package com.example.inventoryApp.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UserId")
    private long userId;

    @Column(name = "UserName")
    private String userName;

    @ManyToOne(targetEntity = Location.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "LocationId", referencedColumnName = "LocationId")
    private Location location;

    @OneToMany(targetEntity = Ownership.class, cascade = CascadeType.ALL)
    private List<Ownership> ownerships;

    @Column(name = "Email")
    private String email;

    @Column(name = "EmploymentStartDate")
    private Date employmentStartDate;

    public User(String name) {
        userName = name;
    }

    public long getId() {
        return userId;
    }
}
