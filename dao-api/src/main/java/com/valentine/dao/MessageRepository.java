package com.valentine.dao;

import com.valentine.messenger.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageDao  extends CrudRepository<Message,Integer> {
}
