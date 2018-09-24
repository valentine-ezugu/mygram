package com.valentine.model;

import com.valentine.messenger.UserGroup;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    //last known ip of user
    private String last_ip;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    //(Unix Timestamp or DateTime), Last time this user was updated?
    private LocalDateTime dateUpdated;

    private boolean isActive;

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL, orphanRemoval=true)
    private Set<UserGroup> userGroup;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLast_ip() {
        return last_ip;
    }

    public void setLast_ip(String last_ip) {
        this.last_ip = last_ip;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + username + '\'' + ", email='" + email + '\'' + ", password='" + password + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", last_ip='" + last_ip + '\'' + ", dateCreated=" + dateCreated + ", dateUpdated=" + dateUpdated + ", isActive=" + isActive + ", userGroup=" + userGroup + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return isActive() == user.isActive() && Objects.equals(getId(), user.getId()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && Objects.equals(getLast_ip(), user.getLast_ip()) && Objects.equals(getDateCreated(), user.getDateCreated()) && Objects.equals(getDateUpdated(), user.getDateUpdated()) && Objects.equals(userGroup, user.userGroup);
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getUsername(), getEmail(), getPassword(), getFirstName(), getLastName(), getLast_ip(), getDateCreated(), getDateUpdated(), isActive(), userGroup);
    }
}
