package za.co.chatbox.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.chatbox.dto.ChatMessageDto;
import za.co.chatbox.exception.ChatNotFoundException;
import za.co.chatbox.exception.UserNotFoundException;
import za.co.chatbox.model.ChatMessage;
import za.co.chatbox.model.ChatUser;
import za.co.chatbox.repo.ChatMessageRepository;
import za.co.chatbox.repo.UserRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j

public class ChatServiceImpl implements ChatService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Override
    public void sendMessage(ChatMessageDto message) {
        ChatUser user = userRepository.findByName(message.getSender());
        if (user != null) {
            ChatMessage cm = new ChatMessage();
            cm.setSender(message.getSender());
            cm.setContent(message.getContent());

            chatMessageRepository.save(cm);

            rabbitTemplate.convertAndSend("chat.exchange", "", message);
        } else {
            throw new UserNotFoundException("User not found with username: " + message.getSender());
        }

    }

    @Override
    @RabbitListener(queues = "chat.queue")
    public void receiveMessage(ChatMessage message) {
        log.info(message.getSender() + " @ " + new Date());
        log.info("~ " + message.getContent());
    }

    @Override
    public List<ChatMessage> readMessages() {
        return chatMessageRepository.findAll();
    }

    @Override
    public void deleteMessagesForUser(String user) {
        List<ChatMessage> chatsBySender = chatMessageRepository.findBySender(user);
        if (chatsBySender != null && chatsBySender.size() > 0) {
            chatMessageRepository.deleteBySender(user);
        } else {
            throw new ChatNotFoundException("No chats available for user: " + user);
        }
    }
}
