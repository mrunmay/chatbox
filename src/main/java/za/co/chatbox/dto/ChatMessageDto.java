package za.co.chatbox.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ChatMessageDto implements Serializable {

    private String sender;

    private String content;

    private Date createdDate;
}
