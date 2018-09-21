package com.valentine.messenger;

import com.valentine.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String subject;

    @ManyToOne
    @Column(name = "creatorId")
    private User user;

    private String messageBody;

    private LocalDateTime dateCreated;

    private Integer parentMessageId;

    private LocalDateTime expiryDate;

    private Integer isReminder;

    private LocalDateTime nextRemindDate;

    private Integer remindFrequencyId;



}
