package com.valentine.model;

import javax.persistence.*;

@Entity(name = "photo_tags")
public class PhotoTags {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer photoTags_Id;

    //this will be used later to get list of photos that have been tagged with a particular tag
    @OneToMany
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @OneToMany
    @JoinColumn(name = "user_id")
    private User user;

    //(Integer), ID of the tag being assigned to a photo
    @OneToMany
    @JoinColumn(name = "tag_id")
    private Tags tags;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    public Integer getPhotoTags_Id() {
        return photoTags_Id;
    }

    public void setPhotoTags_Id(Integer photoTags_Id) {
        this.photoTags_Id = photoTags_Id;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

}