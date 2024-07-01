package com.example.PubSub.dto;

public record SubscribeRequest(
        String topicName,
        String subscriberId
){}