package com.example.PubSub.controller;


import com.example.PubSub.dto.ApiResponse;
import com.example.PubSub.dto.PublishRequest;
import com.example.PubSub.dto.SubscribeRequest;
import com.example.PubSub.dto.UnsubscribeRequest;
import com.example.PubSub.model.Message;
import com.example.PubSub.model.Topic;
import com.example.PubSub.service.PubSubService;
import com.example.PubSub.service.Publisher;
import com.example.PubSub.service.Subscriber;
import com.example.PubSub.service.SubscriberImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pubsub")
public class PubSubController {

    private final PubSubService pubSubService;
    private final Publisher publisher;

    @Autowired
    public PubSubController(PubSubService pubSubService, Publisher publisher) {
        this.pubSubService = pubSubService;
        this.publisher = publisher;
    }

    @PostMapping("/publish")
    public ResponseEntity<?> publish(@RequestBody PublishRequest request) {
        Topic topic = new Topic(request.message().topic().name());
        Message message = new Message(request.message().id(), request.message().content(), topic);
        publisher.publish(message);
        return ResponseEntity.ok(new ApiResponse("success", "Message published"));
    }

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(@RequestBody SubscribeRequest request) {
        Subscriber subscriber = new SubscriberImpl(request.subscriberId());
        pubSubService.addSubscriber(request.topicName(), subscriber);
        return ResponseEntity.ok(new ApiResponse("success", "Subscribed to topic"));
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<?> unsubscribe(@RequestBody UnsubscribeRequest request) {
        Subscriber subscriber = new SubscriberImpl(request.subscriberId());
        pubSubService.removeSubscriber(request.topicName(), subscriber);
        return ResponseEntity.ok(new ApiResponse("success", "Unsubscribed from topic"));
    }
}