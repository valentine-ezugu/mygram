package com.valentine.model;

import javax.persistence.*;

@Entity
public class PhotoComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //(Integer), ID of the photo
    @OneToOne
    @JoinColumn(name = "photo_id")
    private Photo photo;

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
