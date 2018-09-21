package com.valentine.model;

import javax.persistence.*;
import java.util.List;

/**
 *
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "id_generator", sequenceName = "user_Id_sequence", initialValue = 23457)
    private Integer id;

    private String username;

    private String email;

    private String password;

    private String first_name;

    private String last_name;

    //last known ip of user
    private String last_ip;

    // (Unix Timestamp or DateTime), When did this user sign up?
    private String date_created;

    //(Unix Timestamp or DateTime), Last time this user was updated?
    private String date_updated;

    private boolean isActive;

    @OneToMany
    private List<Photo> photo;

    public List<Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(List<Photo> photo) {
        this.photo = photo;
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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLast_ip() {
        return last_ip;
    }

    public void setLast_ip(String last_ip) {
        this.last_ip = last_ip;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(String date_updated) {
        this.date_updated = date_updated;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id
            + ", username='" + username
            + '\'' + ", email='" + email
            + '\'' + ", password='" + password
            + '\'' + ", first_name='" + first_name
            + '\'' + ", last_name='" + last_name
            + '\'' + ", last_ip='" + last_ip
            + '\'' + ", date_created='" + date_created
            + '\'' + ", date_updated='" + date_updated
            + '\'' + ", isActive=" + isActive
            + ", photo=" + photo + '}';
    }
}
