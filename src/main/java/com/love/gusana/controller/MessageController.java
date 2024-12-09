package com.love.gusana.controller;

import com.love.gusana.model.Message;
import com.love.gusana.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message){

    }

    @GetMapping
    public ResponseEntity<Message> searchMessageByID(@RequestBody Message message){

    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@RequestBody Message message){

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteMessage(@RequestBody Message message){

    }

}
