package com.valentine.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    //TODO cross check likes and user if its valid to have them here
    @OneToMany(fetch = FetchType.LAZY)
    private List<Likes> likes;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
        name="photo_comments",
        joinColumns={@JoinColumn(name="photo_id", referencedColumnName="id")},
        inverseJoinColumns={@JoinColumn(name="comment_id", referencedColumnName="id")})
    private Set<Comment> comments;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "photos_hashtags",
        joinColumns ={@JoinColumn(name="photo_id", referencedColumnName="id")},
        inverseJoinColumns={@JoinColumn(name="hashtag_id", referencedColumnName="id")})
    private Set<HashTag> hashTags;


    @ManyToMany
    private Set<Tags> tags;

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Tags> getTags() {
        return tags;
    }

    public void setTags(Set<Tags> tags) {
        this.tags = tags;
    }

    public Set<HashTag> getHashTags() {
        return hashTags;
    }

    public void setHashTags(Set<HashTag> hashTags) {
        this.hashTags = hashTags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Likes> getLikes() {
        return likes;
    }

    public void setLikes(List<Likes> likes) {
        this.likes = likes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
