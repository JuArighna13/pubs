package com.example.PubSub.service;

import com.example.PubSub.model.Message;
import org.springframework.stereotype.Component;

@Component
public class SubscriberImpl implements Subscriber {
    private String subId;

    public SubscriberImpl() {
    }

    public SubscriberImpl(String subId) {
        this.subId = subId;
    }

    @Override
    public void receive(Message message) {
        System.out.println("Subscriber: " + subId +
                " received message: " + message.content() +
                " from topic: " + message.topic().name() +
                " with offset: " + message.id()
        );
    }
}