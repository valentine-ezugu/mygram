package com.valentine.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // (Integer), Primary ID that preferably auto increments (if supported in chosen DB)
    private Integer photo_id;

   // (Integer), ID of the user who owns this photo (Indexed field)
    private Integer user_id;

   // (String), Photo caption
    private String caption;

   //(Float), Latitude value for location
    private Float latitude;

   //(Float), Longitude value location
    private Float longitude;

   //(String), Path to image on server
    private String image_path;

   //(Integer), Image size on server
    private Integer image_size;

    //(Unix Timestamp or DateTime), When was this image created?
    private LocalDate date_created;

   //(Unix Timestamp or DateTime), Last time this image was updated?
    private LocalDate date_updated;

    @OneToMany(fetch = FetchType.LAZY)
    private Likes likes;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(fetch = FetchType.EAGER)
    private Comment comment;

    private PhotoTags photoTags;


    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public Integer getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(Integer photo_id) {
        this.photo_id = photo_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public Integer getImage_size() {
        return image_size;
    }

    public void setImage_size(Integer image_size) {
        this.image_size = image_size;
    }

    public LocalDate getDate_created() {
        return date_created;
    }

    public void setDate_created(LocalDate date_created) {
        this.date_created = date_created;
    }

    public LocalDate getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(LocalDate date_updated) {
        this.date_updated = date_updated;
    }
}
