package com.example.PubSub.service;

import com.example.PubSub.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Publisher {
    private final PubSubService pubSubService;

    @Autowired
    public Publisher(PubSubService pubSubService) {
        this.pubSubService = pubSubService;
    }

    public void publish(Message message) {
        pubSubService.addMessageToQueue(message);
    }
}