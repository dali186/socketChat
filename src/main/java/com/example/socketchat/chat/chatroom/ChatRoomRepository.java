package com.example.socketchat.chat.chatroom;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

    @Query("{$and: [{memberList: ?0}, {memberList: ?1}]}")
    ChatRoom findDuplicatedRoom(Long memberId, Long opId);

    @Query(value = "{memberList: ?0, type: 'PERSONAL'}")
    List<ChatRoom> findPersonalRoomById(Long memberId);

    @Query("{type: 'GROUP'}")
    List<ChatRoom> findGroupRoomById();

    @Query("{_id: ?0}")
    ChatRoom findRoomById(String roomId);

    @Query("{memberList: ?0, type: ?1}")
    List<ChatRoom> findRoomByType(Long memberId, ChatRoomType type);
}
