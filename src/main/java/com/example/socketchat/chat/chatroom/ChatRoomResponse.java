package com.example.socketchat.chat.chatroom;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class ChatRoomResponse {

    @Getter
    @Setter
    public static class ChatRoomListDTO {
        private String roomId;
        private String roomTitle;
        private String recentMessage;
        private LocalDateTime time;
    }
}
