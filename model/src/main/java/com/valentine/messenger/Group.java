package com.valentine.messenger;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToMany(mappedBy="group", cascade=CascadeType.ALL, orphanRemoval=true)
    private Set<UserGroup> userGroup;

    private boolean isActive;

    @Column(name = "date_created")
    private LocalDateTime dateCreated = LocalDateTime.now();

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

    public Set<UserGroup> getUserGroups() {
        return userGroup;
    }

    public void setUserGroups(Set<UserGroup> userGroups) {
        this.userGroup = userGroups;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
