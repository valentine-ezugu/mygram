package com.valentine.messenger;

import com.valentine.model.User;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String subject;

    @ManyToOne
    private User user;

    private String messageBody;

    private LocalDateTime dateCreated;

    private LocalDateTime expiryDate;

    private Integer isReminder;

    @ManyToOne
    @JoinColumn(name = "parent_message_id")
    private Message parentMessageId;

    private LocalDateTime nextRemindDate;

    private Integer remindFrequencyId;

    public Message(String messageBody) {
        this.messageBody = messageBody;
        this.dateCreated = LocalDateTime.now();
    }

    public Message() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Message getParentMessageId() {
        return parentMessageId;
    }

    public void setParentMessageId(Message parentMessageId) {
        this.parentMessageId = parentMessageId;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getRemindFrequencyId() {
        return remindFrequencyId;
    }

    public void setRemindFrequencyId(Integer remindFrequencyId) {
        this.remindFrequencyId = remindFrequencyId;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getIsReminder() {
        return isReminder;
    }

    public void setIsReminder(Integer isReminder) {
        this.isReminder = isReminder;
    }

    public LocalDateTime getNextRemindDate() {
        return nextRemindDate;
    }

    public void setNextRemindDate(LocalDateTime nextRemindDate) {
        this.nextRemindDate = nextRemindDate;
    }
}
