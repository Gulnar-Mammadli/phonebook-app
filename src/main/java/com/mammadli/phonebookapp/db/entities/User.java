package com.mammadli.phonebookapp.db.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(
            name = "uuid2",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(nullable = false,columnDefinition = "VARCHAR(255)")
   
    private String userId;

    private String name;

    private String phone;

    private boolean deleted= false;

}
