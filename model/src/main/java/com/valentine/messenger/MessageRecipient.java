package com.valentine.messenger;

import com.valentine.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class MessageRecipient implements Serializable {

    @EmbeddedId
    private UserGroupPk userGroupPk = new UserGroupPk();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne @MapsId("userId")
    private User user;


    @ManyToOne
    @MapsId( "usergroupId")//this is where i should have FK of the user_group table
    private UserGroup userGroup;


    @OneToMany
    @MapsId("message_id")
    private List<Message> message;


    private boolean isRead;


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

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public UserGroupPk getUserGroupPk() {
        return userGroupPk;
    }

    public void setUserGroupPk(UserGroupPk userGroupPk) {
        this.userGroupPk = userGroupPk;
    }

    public List<Message> getMessage() {
        return message;
    }

    public void setMessage(List<Message> message) {
        this.message = message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageRecipient)) return false;
        MessageRecipient that = (MessageRecipient) o;
        return isRead() == that.isRead() && Objects.equals(getUserGroupPk(), that.getUserGroupPk()) && Objects.equals(getId(), that.getId()) && Objects.equals(getUser(), that.getUser()) && Objects.equals(getUserGroup(), that.getUserGroup()) && Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUserGroupPk(), getId(), getUser(), getUserGroup(), getMessage(), isRead());
    }
}
