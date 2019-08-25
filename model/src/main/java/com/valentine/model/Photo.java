package com.valentine.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

//@EntityListeners(PhotoListener.class)
@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // (String), Photo caption
    private String caption;

    //(Float), Latitude value for location
    private Float latitude;

    //(Float), Longitude value location
    private Float longitude;

    //original file name
    private String initialName;

    //(String), Path to image on server or url
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
    @JoinTable(name = "photo_comments", joinColumns = {@JoinColumn(name = "photo_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "comment_id", referencedColumnName = "id")})
    private Set<Comment> comments;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "photos_hashtags", joinColumns = {@JoinColumn(name = "photo_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "hashtag_id", referencedColumnName = "id")})
    private Set<HashTag> hashTags;


    @ManyToMany
    private Set<Tags> tags;

    public String getInitialName() {
        return initialName;
    }

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

    public void setInitialName(String initialName) {
        this.initialName = initialName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photo)) return false;

        Photo photo = (Photo) o;

        if (getId() != null ? !getId().equals(photo.getId()) : photo.getId() != null) return false;
        if (getCaption() != null ? !getCaption().equals(photo.getCaption()) : photo.getCaption() != null) return false;
        if (getLatitude() != null ? !getLatitude().equals(photo.getLatitude()) : photo.getLatitude() != null)
            return false;
        if (getLongitude() != null ? !getLongitude().equals(photo.getLongitude()) : photo.getLongitude() != null)
            return false;
        if (getInitialName() != null ? !getInitialName().equals(photo.getInitialName()) : photo.getInitialName() != null)
            return false;
        if (getImage_path() != null ? !getImage_path().equals(photo.getImage_path()) : photo.getImage_path() != null)
            return false;
        if (getImage_size() != null ? !getImage_size().equals(photo.getImage_size()) : photo.getImage_size() != null)
            return false;
        if (getDate_created() != null ? !getDate_created().equals(photo.getDate_created()) : photo.getDate_created() != null)
            return false;
        if (getDate_updated() != null ? !getDate_updated().equals(photo.getDate_updated()) : photo.getDate_updated() != null)
            return false;
        if (getLikes() != null ? !getLikes().equals(photo.getLikes()) : photo.getLikes() != null) return false;
        if (getUser() != null ? !getUser().equals(photo.getUser()) : photo.getUser() != null) return false;
        if (getComments() != null ? !getComments().equals(photo.getComments()) : photo.getComments() != null)
            return false;
        if (getHashTags() != null ? !getHashTags().equals(photo.getHashTags()) : photo.getHashTags() != null)
            return false;
        return getTags() != null ? getTags().equals(photo.getTags()) : photo.getTags() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getCaption() != null ? getCaption().hashCode() : 0);
        result = 31 * result + (getLatitude() != null ? getLatitude().hashCode() : 0);
        result = 31 * result + (getLongitude() != null ? getLongitude().hashCode() : 0);
        result = 31 * result + (getInitialName() != null ? getInitialName().hashCode() : 0);
        result = 31 * result + (getImage_path() != null ? getImage_path().hashCode() : 0);
        result = 31 * result + (getImage_size() != null ? getImage_size().hashCode() : 0);
        result = 31 * result + (getDate_created() != null ? getDate_created().hashCode() : 0);
        result = 31 * result + (getDate_updated() != null ? getDate_updated().hashCode() : 0);
        result = 31 * result + (getLikes() != null ? getLikes().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getComments() != null ? getComments().hashCode() : 0);
        result = 31 * result + (getHashTags() != null ? getHashTags().hashCode() : 0);
        result = 31 * result + (getTags() != null ? getTags().hashCode() : 0);
        return result;
    }
}
