package com.example.socketchat.chat.chatmessage;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Tailable;

import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    @Query("{roomId: ?0}")
    List<ChatMessage> mFindByRoomId(String Id);

    @Query("{roomId:  ?0}")
    Optional<ChatMessage> findFirstByRoomIdOrderByCreatedAtDesc(String id);
}
