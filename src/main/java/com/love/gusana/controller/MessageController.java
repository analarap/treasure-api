package com.love.gusana.controller;

import com.love.gusana.model.Message;
import com.love.gusana.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message){
        Message newMessage = messageService.saveMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMessage);
    }

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages(){
        List<Message> messageList = messageService.listAll();
        return ResponseEntity.ok(messageList);
    }

    @GetMapping
    public ResponseEntity<Message> searchMessageByID(@PathVariable Long id){
        return messageService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @RequestBody Message updatedMessage){
        return messageService.updateMessage(id, updatedMessage)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteMessage(@PathVariable Long id){
        if (messageService.deleteMessage(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
