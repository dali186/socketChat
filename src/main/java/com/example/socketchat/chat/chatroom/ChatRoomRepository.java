package com.example.socketchat.chat.chatroom;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

    @Query("{memberList: ?0}")
    List<ChatRoom> mFindRoomById(Long memberId);

    @Query("{memberList: ?0, type: 'PERSONAL'}")
    List<ChatRoom> findPersonalRoomById(Long memberId);

    @Query("{memberList:  ?0}")
    List<ChatRoom> findMemberRoomById(Long memberId);
}
