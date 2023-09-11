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
        //중복처리 검증 로직
        ChatRoom duplicatedRoom = chatRoomRepository.findDuplicatedRoom(chatRoom.getMemberList().get(0), chatRoom.getMemberList().get(1));
        if (duplicatedRoom == null) {
            ChatRoom savedRoom = chatRoomRepository.save(chatRoom);
            return savedRoom.getId();
        }
        return duplicatedRoom.getId();
    }

    @Transactional
    public List<ChatRoom> findMemberChatRooms(Long memberId) {
        List<ChatRoom> chatRooms = chatRoomRepository.findMemberRoomById(memberId);
        return chatRooms;
    }

    @Transactional
    public List<ChatRoom> findMemberPersonalChatRooms(Long memberId) {
        List<ChatRoom> chatRooms = chatRoomRepository.findPersonalRoomById(memberId);
        return chatRooms;
    }

    @Transactional
    public ChatRoom findChatRoomById(String roomId) {
        return chatRoomRepository.findByRoomById(roomId);
    }
}
