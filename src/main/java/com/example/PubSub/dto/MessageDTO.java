package com.example.PubSub.dto;

public record MessageDTO(
        String id,
        String content,
        TopicDTO topic
){}
