package com.valentine.messenger;

import com.valentine.model.User;

import javax.persistence.*;

@Entity
public class MessageRecipient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "recipient_Id")
    private User user;

    @ManyToOne
    @Column(name = "recipient_group_id")//this is where i should have FK of the user_group table
    private UserGroup userGroup;

    @Column(name = "message_id")
    private Message message;

    private boolean isRead;
}
