package com.love.gusana.service;

import com.love.gusana.model.Message;
import com.love.gusana.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message saveMessage(Message message){
        return messageRepository.save(message);
    }

    public List<Message> listAll(){
        return messageRepository.findAll();
    }

    public Optional<Message> findById(Long id){
        return messageRepository.findById(id);
    }

    public Optional<Message> updateMessage(Long id, Message updatedMessage){
        return messageRepository.findById(id).map(messageExisting -> {
            messageExisting.setContent(updatedMessage.getContent());
            messageExisting.setAuthor(updatedMessage.getAuthor());

            return messageRepository.save(messageExisting);
        });
    }

    public Optional<Message> searchAndMarkAsRead(Long id){
        Optional<Message> message = messageRepository.findById(id);
        message.ifPresent(msg -> messageRepository.markAsread(id));
        return message;
    }

    public boolean deleteMessage(Long id){
        if (messageRepository.existsById(id)){
            messageRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
