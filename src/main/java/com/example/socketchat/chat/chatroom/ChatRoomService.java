package com.example.socketchat.chat.chatroom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public String openChatRoom(ChatRoom chatRoom) {
        ChatRoom savedRoom = chatRoomRepository.save(chatRoom);
        return savedRoom.getId();
    }

    @Transactional
    public List<ChatRoom> findMemberChatRooms(Long memberId) {
        List<ChatRoom> chatRooms = chatRoomRepository.findMemberRoomById(memberId);
        return chatRooms;
    }
}
