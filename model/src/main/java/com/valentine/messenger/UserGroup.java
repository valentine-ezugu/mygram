package com.valentine.messenger;

import com.valentine.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany
    private Set<User> users;

    @OneToMany
    private Set<Group> group;

    private LocalDateTime dated_created = LocalDateTime.now();

    private boolean isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Group> getGroups() {
        return group;
    }

    public void setGroups(Set<Group> groups) {
        this.group = groups;
    }

    public LocalDateTime getDated_created() {
        return dated_created;
    }

    public void setDated_created(LocalDateTime dated_created) {
        this.dated_created = dated_created;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


}
