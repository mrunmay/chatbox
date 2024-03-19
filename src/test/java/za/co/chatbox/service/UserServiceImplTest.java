package za.co.chatbox.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import za.co.chatbox.dto.ChatUserDto;
import za.co.chatbox.model.ChatUser;
import za.co.chatbox.repo.UserRepository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_NewUser_UserRegisteredSuccessfully() {
        ChatUserDto newUserDto = new ChatUserDto();
        newUserDto.setName("newUser");
        newUserDto.setPassword("password");
        newUserDto.setEmail("newUser@example.com");

        when(userRepository.findByName("newUser")).thenReturn(null);

        assertDoesNotThrow(() -> userService.registerUser(newUserDto));

        verify(userRepository, times(1)).save(any(ChatUser.class));
    }
}
