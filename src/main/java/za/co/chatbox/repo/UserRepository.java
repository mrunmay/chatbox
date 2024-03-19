package za.co.chatbox.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.chatbox.model.ChatUser;

public interface UserRepository extends JpaRepository<ChatUser, Long> {
    ChatUser findByName(String username);
}
