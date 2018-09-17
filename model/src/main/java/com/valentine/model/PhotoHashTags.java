package com.valentine.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PhotoHashTags {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)// (Integer), ID of the photo
    private Integer photo_id;

     //(Integer), ID of the hashtag being assigned to a photo
    private Integer hashtag_id;

    public Integer getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(Integer photo_id) {
        this.photo_id = photo_id;
    }

    public Integer getHashtag_id() {
        return hashtag_id;
    }

    public void setHashtag_id(Integer hashtag_id) {
        this.hashtag_id = hashtag_id;
    }

}
