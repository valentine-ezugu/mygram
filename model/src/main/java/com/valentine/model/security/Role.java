package com.valentine.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

   // ogbonno- 15 zl 75
   // egusi-  80 gram 8 zlb  300 gram   60
   // beans - 2 kg 30l  60 zl
   // bitter leaf -
   // stock fish  -
   // crayfish
}
