
package com.example.inventoryApp;

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

    
    public User (String name) {
        this.name = name;
        this.status = "Active";

        
    }
    
    
}
