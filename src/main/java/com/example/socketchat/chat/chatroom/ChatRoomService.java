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
        //중복처리 검증 로직, TODO 개인/그룹 분리하기
        if (chatRoom.getType().equals(ChatRoomType.PERSONAL)) {
            return duplicatedCheckByPersonal(chatRoom);
        } else {
            //TODO 그룹채팅 중복처리 메소드 구현
            ChatRoom savedRoom = chatRoomRepository.save(chatRoom);
            return savedRoom.getId();
        }
    }

    private String duplicatedCheckByPersonal(ChatRoom chatRoom) {
        ChatRoom duplicatedRoom = chatRoomRepository.findDuplicatedRoom(chatRoom.getMemberList().get(0), chatRoom.getMemberList().get(1));
        if (duplicatedRoom == null) {
            ChatRoom savedRoom = chatRoomRepository.save(chatRoom);
            return savedRoom.getId();
        }
        return duplicatedRoom.getId();
    }

    @Transactional
    public ChatRoom findChatRoomById(String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }

    @Transactional
    public List<ChatRoom> findChatRoomByChatRoomType(Long memberId, ChatRoomType type) {
        return chatRoomRepository.findRoomByType(memberId, type);
    }

    @Transactional
    public List<ChatRoom> findGroupChatRooms() {
        List<ChatRoom> chatRooms = chatRoomRepository.findGroupRoomById();
        return chatRooms;
    }

    @Transactional
    public List<ChatRoom> findMemberPersonalChatRooms(Long memberId) {
        List<ChatRoom> chatRooms = chatRoomRepository.findPersonalRoomById(memberId);
        return chatRooms;
    }
}
