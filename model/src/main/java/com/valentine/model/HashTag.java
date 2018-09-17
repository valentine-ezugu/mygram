package com.valentine.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "hash_tag")
public class HashTag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer hashtag_id;

    private String hashtagname;

    public Integer getHashtag_id() {
        return hashtag_id;
    }

    public void setHashtag_id(Integer hashtag_id) {
        this.hashtag_id = hashtag_id;
    }

    public String getHashtagname() {
        return hashtagname;
    }

    public void setHashtagname(String hashtagname) {
        this.hashtagname = hashtagname;
    }

}
