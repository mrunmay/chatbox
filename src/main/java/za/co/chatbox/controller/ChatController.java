package za.co.chatbox.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.chatbox.dto.ChatMessageDto;
import za.co.chatbox.model.ChatMessage;
import za.co.chatbox.service.ChatService;

import java.util.List;

@RestController
@RequestMapping("api/message")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/send")
    public void sendMessage(@RequestBody ChatMessageDto message) {
        chatService.sendMessage(message);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ChatMessage>> getMessages() {
        return new ResponseEntity<>(chatService.readMessages(), HttpStatus.OK);
    }

    @GetMapping("/deleteAllFor/{user}")
    public void deleteMessagesForUser(@PathVariable(name = "user") String user) {
        chatService.deleteMessagesForUser(user);
    }
}
