package com.example.PubSub.dto;

public record UnsubscribeRequest(
        String topicName,
        String subscriberId
){}
