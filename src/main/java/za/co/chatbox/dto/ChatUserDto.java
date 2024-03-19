    package za.co.chatbox.dto;

    import lombok.Getter;
    import lombok.Setter;

    import java.io.Serializable;

    @Getter
    @Setter
    public class ChatUserDto implements Serializable {

        private String name;

        private String password;

        private String email;
    }
