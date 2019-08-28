package com.voyager.app.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="username")
    private String username;

    private String email;
    
    @Column(name="password")
    private String password;

    @OneToOne(cascade = {CascadeType.ALL})
    private Profile profile;
    private String status;
    private Date creationDate;
    private Date lastLoginDate;

    @OneToOne(cascade = {CascadeType.ALL})
    private OtpEmailConfirmation otpEmailConfirmation;

    @ManyToMany(mappedBy = "userManagements")
    private Set<Project> projectsMg;

    @ManyToMany(mappedBy = "members")
    private Set<Project> projectsMembers;

    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles  = new HashSet<>();
    
    public User() { }

}
