package com.valentine.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Likes {

    @Id
    // ID of the user performing the like (Indexed field)
    private Integer user_id;

   //(Integer), ID of the photo being liked (Indexed field)
    private Integer photo_id;

   //(Unix Timestamp or DateTime), When was this image created?
    private LocalDateTime date_created;

   //(Unix Timestamp or DateTime), Last time this image was updated?
    private LocalDateTime date_updated;

    @OneToOne
    private com.valentine.model.Photo Photo;

    public com.valentine.model.Photo getPhoto() {
        return Photo;
    }

    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPhoto(com.valentine.model.Photo photo) {
        Photo = photo;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(Integer photo_id) {
        this.photo_id = photo_id;
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
