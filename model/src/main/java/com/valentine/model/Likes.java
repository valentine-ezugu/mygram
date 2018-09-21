package com.valentine.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

   //(Unix Timestamp or DateTime), When was this image created?
    private LocalDateTime date_created;

   //(Unix Timestamp or DateTime), Last time this image was updated?
    private LocalDateTime date_updated;

    @ManyToOne
    private Photo photo;

    public Photo getPhoto() {
        return photo;
    }

    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPhoto(Photo photo) {
        photo = photo;
    }

    public LocalDateTime getDate_created() {
        return date_created;
    }

    public void setDate_created(LocalDateTime date_created) {
        this.date_created = date_created;
    }

    public LocalDateTime getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(LocalDateTime date_updated) {
        this.date_updated = date_updated;
    }
}
