package za.co.chatbox.service;

import za.co.chatbox.dto.ChatMessageDto;
import za.co.chatbox.model.ChatMessage;

import java.util.List;

public interface ChatService {

    void sendMessage(ChatMessageDto message);

    void receiveMessage(ChatMessage message);

    List<ChatMessage> readMessages();

    void deleteMessagesForUser(String user);
}

