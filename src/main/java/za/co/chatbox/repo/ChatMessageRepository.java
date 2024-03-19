package za.co.chatbox.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.chatbox.model.ChatMessage;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findBySender(String username);

    void deleteBySender(String user);
}
