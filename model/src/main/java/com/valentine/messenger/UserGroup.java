package com.valentine.messenger;

import com.valentine.model.User;

import javax.persistence.*;
import java.util.Set;

@Entity
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany
    private Set<User> users;

    @OneToMany
    private Set<Group> groups;

}
