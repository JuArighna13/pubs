package com.example.PubSub.service;

import com.example.PubSub.model.Message;

public interface Subscriber {
    void receive(Message message);
}