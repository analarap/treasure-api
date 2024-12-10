package com.love.gusana.service;

import com.love.gusana.exception.InvalidRequestException;
import com.love.gusana.exception.ResourceNotFoundException;
import com.love.gusana.model.Message;
import com.love.gusana.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message saveMessage(Message message) {
        if (message.getContent() == null || message.getContent().isEmpty()) {
            throw new InvalidRequestException("The message's content can't be empty.");
        }
        if (message.getAuthor() == null || message.getAuthor().isEmpty()) {
            throw new InvalidRequestException("The message's author can't be empty.");
        }
        message.setDateCreation(LocalDateTime.now());
        return messageRepository.save(message);
    }


    public List<Message> listAll() {
        List<Message> messages = messageRepository.findAll();
        if (messages.isEmpty()) {
            throw new ResourceNotFoundException("No messages found.");
        }
        return messages;
    }

    public Optional<Message> findById(Long id) {
        return Optional.ofNullable(messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message with id " + id + " not found.")));
    }


    public Optional<Message> updateMessage(Long id, Message updatedMessage) {
        if (updatedMessage.getContent() == null || updatedMessage.getContent().isEmpty()) {
            throw new InvalidRequestException("The updated message's content can't be empty.");
        }

        return messageRepository.findById(id).map(existingMessage -> {
                    existingMessage.setContent(updatedMessage.getContent());
                    existingMessage.setAuthor(updatedMessage.getAuthor());
                    return messageRepository.save(existingMessage);
                }).map(updated -> Optional.of(updated))
                .orElseThrow(() -> new ResourceNotFoundException("Message with id " + id + " not found."));
    }


    public Optional<Message> searchAndMarkAsRead(Long id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message with id " + id + " not found."));
        messageRepository.markAsread(id);
        return Optional.of(message);
    }

    public boolean deleteMessage(Long id) {
        if (messageRepository.existsById(id)) {
            messageRepository.deleteById(id);
            return true;
        }
        throw new ResourceNotFoundException("Message with id " + id + " not found.");
    }
}
