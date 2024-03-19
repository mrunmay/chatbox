package za.co.chatbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.chatbox.dto.ChatUserDto;
import za.co.chatbox.model.ChatUser;
import za.co.chatbox.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public void registerUser(ChatUserDto user) {
        ChatUser cu = new ChatUser();
        cu.setName(user.getName());
        cu.setPassword(user.getPassword());
        cu.setEmail(user.getEmail());

        userRepository.save(cu);
    }
}
