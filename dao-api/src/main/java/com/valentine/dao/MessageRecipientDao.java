package com.valentine.dao;

import com.valentine.messenger.MessageRecipient;
import org.springframework.data.repository.CrudRepository;

public interface MessageRecipientDao extends CrudRepository<MessageRecipient,Integer> {

}
