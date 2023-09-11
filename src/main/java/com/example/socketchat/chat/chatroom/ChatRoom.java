package com.example.socketchat.chat.chatroom;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "chatRoom")
public class ChatRoom {
    @Id
    private String id;
    private ChatRoomType type;
    private String title;
    private String notice;
    private String contentNotice;
    private List<Long> memberList;

    public ChatRoom() {
        this.memberList = new ArrayList<>();
    }
}
