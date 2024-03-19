package za.co.chatbox.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.AmqpTemplate;
import za.co.chatbox.dto.ChatMessageDto;
import za.co.chatbox.exception.ChatNotFoundException;
import za.co.chatbox.exception.UserNotFoundException;
import za.co.chatbox.model.ChatMessage;
import za.co.chatbox.model.ChatUser;
import za.co.chatbox.repo.ChatMessageRepository;
import za.co.chatbox.repo.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ChatServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ChatMessageRepository chatMessageRepository;

    @Mock
    private AmqpTemplate rabbitTemplate;

    @InjectMocks
    private ChatServiceImpl chatService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendMessage_UserExists_MessageSentAndSaved() {
        ChatMessageDto messageDto = new ChatMessageDto();
        messageDto.setSender("testuser");
        messageDto.setContent("Hello!");

        ChatUser user = new ChatUser();
        user.setName("testuser");

        when(userRepository.findByName("testuser")).thenReturn(user);

        chatService.sendMessage(messageDto);

        verify(chatMessageRepository, times(1)).save(any(ChatMessage.class));
        verify(rabbitTemplate, times(1)).convertAndSend(anyString(), anyString(), any(ChatMessageDto.class));
    }

    @Test
    void sendMessage_UserNotFound_ExceptionThrown() {
        ChatMessageDto messageDto = new ChatMessageDto();
        messageDto.setSender("nonexistentuser");
        messageDto.setContent("Hello!");

        when(userRepository.findByName("nonexistentuser")).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> chatService.sendMessage(messageDto));
    }

    @Test
    void deleteMessagesForUser_UserHasChats_ChatsDeleted() {
        List<ChatMessage> messages = new ArrayList<>();
        messages.add(new ChatMessage());
        when(chatMessageRepository.findBySender("testuser")).thenReturn(messages);

        chatService.deleteMessagesForUser("testuser");

        verify(chatMessageRepository, times(1)).deleteBySender("testuser");
    }

    @Test
    void deleteMessagesForUser_NoChatsForUser_ExceptionThrown() {
        when(chatMessageRepository.findBySender("nonexistentuser")).thenReturn(new ArrayList<>());

        assertThrows(ChatNotFoundException.class, () -> chatService.deleteMessagesForUser("nonexistentuser"));
    }
}
