package com.valentine.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   //(Unix Timestamp or DateTime), When was this image created?
    private LocalDateTime date_created;

   //(Unix Timestamp or DateTime), Last time this image was updated?
    private LocalDateTime date_updated;

    //photo_id
    @ManyToOne
    private Photo photo;

    public Photo getPhoto() {
        return photo;
    }

    //user_id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Likes)) return false;
        Likes likes = (Likes) o;
        return Objects.equals(getId(), likes.getId()) && Objects.equals(getDate_created(), likes.getDate_created()) && Objects.equals(getDate_updated(), likes.getDate_updated()) && Objects.equals(getPhoto(), likes.getPhoto()) && Objects.equals(getUser(), likes.getUser());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDate_created(), getDate_updated(), getPhoto(), getUser());
    }
}
