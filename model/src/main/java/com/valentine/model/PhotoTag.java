package com.valentine.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "photo_tags")
public class PhotoTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer photoTags_Id;

    //this will be used later to get list of photos that have been tagged with a particular tag
    @OneToMany
    @JoinColumn(name = "photo_id")
    private List<Photo> photos;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<User> users;

    //(Integer), ID of the tag being assigned to a photo
    @OneToMany
    @JoinColumn(name = "tag_id")
    private List<Tags> tags;

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public Integer getPhotoTags_Id() {
        return photoTags_Id;
    }

    public void setPhotoTags_Id(Integer photoTags_Id) {
        this.photoTags_Id = photoTags_Id;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}