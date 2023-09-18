package com.example.socketchat.chat.chatroom;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomRequest {

    @Getter
    @Setter
    public static class GroupRoomDetailsDTO {
        private String title;
        private List<Long> memberList;

        public GroupRoomDetailsDTO (String title, String memberList) {
            this.title = title;
            this.memberList = new ArrayList<>();
        }
    }
}
