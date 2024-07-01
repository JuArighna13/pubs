package com.example.PubSub.model;

public record Message(
        String id,
        String content,
        Topic topic
) {
}
