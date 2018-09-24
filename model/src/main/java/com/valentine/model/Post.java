package com.valentine.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @ManyToOne @MapsId("userId")
    private User user;

    @OneToMany
    private Set<Photo> photo;

    @Column
    LocalDateTime dateCreated = LocalDateTime.now();

    public Post() {
    }

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

    public Set<Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(Set<Photo> photo) {
        this.photo = photo;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return Objects.equals(getId(), post.getId()) && Objects.equals(getUser(), post.getUser()) && Objects.equals(getPhoto(), post.getPhoto()) && Objects.equals(getDateCreated(), post.getDateCreated());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getUser(), getPhoto(), getDateCreated());
    }
}
