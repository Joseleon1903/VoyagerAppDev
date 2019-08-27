package com.voyager.app.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *  {@link User} roles.
 *
 */
@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users =new HashSet<>();

}
