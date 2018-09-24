package com.valentine.messenger;

import com.valentine.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @MapsId(value = "user_id")
    private User user;

    @ManyToOne
    @MapsId(value = "groupId")
    private Group group;

    private LocalDateTime dated_created;

    private boolean isActive;


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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public LocalDateTime getDated_created() {
        return dated_created;
    }

    public void setDated_created(LocalDateTime dated_created) {
        this.dated_created = dated_created;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserGroup)) return false;
        UserGroup userGroup = (UserGroup) o;
        return isActive() == userGroup.isActive() && Objects.equals(getId(), userGroup.getId()) && Objects.equals(getUser(), userGroup.getUser()) && Objects.equals(getGroup(), userGroup.getGroup()) && Objects.equals(getDated_created(), userGroup.getDated_created());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getUser(), getGroup(), getDated_created(), isActive());
    }
}