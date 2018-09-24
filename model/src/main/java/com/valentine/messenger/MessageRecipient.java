package com.valentine.messenger;

import com.valentine.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
public class MessageRecipient implements Serializable {

    @EmbeddedId
    private UserGroupPk userGroupPk;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @MapsId("userId")
    private User recipient;

    @ManyToOne
    @MapsId("groupId")//this is where i should have FK of the user_group table
    private UserGroup recipient_group;

    @ManyToOne
    private Message message;

    private boolean isRead;

    public UserGroupPk getUserGroupPk() {
        return userGroupPk;
    }

    public void setUserGroupPk(UserGroupPk userGroupPk) {
        this.userGroupPk = userGroupPk;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserGroup getRecipient_group() {
        return recipient_group;
    }

    public void setRecipient_group(UserGroup recipient_group) {
        this.recipient_group = recipient_group;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
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
        return isRead() == that.isRead() && Objects.equals(getUserGroupPk(), that.getUserGroupPk()) && Objects.equals(getId(), that.getId()) && Objects.equals(getRecipient(), that.getRecipient()) && Objects.equals(getRecipient_group(), that.getRecipient_group()) && Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUserGroupPk(), getId(), getRecipient(), getRecipient_group(), getMessage(), isRead());
    }
}
