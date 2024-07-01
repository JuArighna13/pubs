package com.example.PubSub.service;

import com.example.PubSub.model.Message;
import org.springframework.stereotype.Service;

import java.util.*;
        import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class PubSubService {
    private final Queue<Message> messageQueue = new ConcurrentLinkedQueue<>();
    private final Map<String, List<Subscriber>> subscribersTopicMap = new ConcurrentHashMap<>();

    public void addMessageToQueue(Message message) {
        messageQueue.add(message);
        broadcast();
    }

    public void addSubscriber(String topicName, Subscriber subscriber) {
        subscribersTopicMap.computeIfAbsent(topicName, k -> new ArrayList<>())
                .add(subscriber);
    }

    public void removeSubscriber(String topicName, Subscriber subscriber) {
        Optional.ofNullable(subscribersTopicMap.get(topicName))
                .ifPresent(subscribers -> subscribers.remove(subscriber));
    }

    private void broadcast() {
        while (!messageQueue.isEmpty()) {
            Message message = messageQueue.poll();
            String topicName = message.topic().name();
            subscribersTopicMap.getOrDefault(topicName, new ArrayList<>())
                    .forEach(subscriber -> subscriber.receive(message));
        }
    }
}
