package com.TradeProject.TradeEngine.redis.resource;

import com.TradeProject.TradeEngine.redis.service.TradeMessagePublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/messages")
@RestController
public class MessageController {
    private final TradeMessagePublisher messagePublisher;

    public MessageController(TradeMessagePublisher trademessagePublisher){
        this.messagePublisher = trademessagePublisher;
    }

    @PostMapping
    public ResponseEntity<String> createMessage(@RequestBody String message){
        this.messagePublisher.publish(message);

        return ResponseEntity.ok(message);
    }
}
