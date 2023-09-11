package com.example.socketchat.chat.chatroom;

import com.example.socketchat.chat.chatmessage.ChatMessage;
import com.example.socketchat.chat.chatmessage.ChatMessageRepository;
import com.example.socketchat.member.MemberService;
import com.example.socketchat.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ChatMessageRepository chatMessageRepository;
    private final MemberService memberService;

    //1:1 채팅방 생성
    //TODO 중복처리, 채팅방 타이틀 설정하기
    @GetMapping("/chat/open/p/{memberId}")
    public String openPersonalRoom(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long memberId) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.getMemberList().add(userDetails.getUserId());
        chatRoom.getMemberList().add(memberId);

        chatRoom.setType(ChatRoomType.PERSONAL);
        chatRoom.setTitle(userDetails.getUserId() + " and " + memberId);

        String roomId = chatRoomService.openChatRoom(chatRoom);

        return "redirect:/chat/room/" + roomId;
    }

    //채팅방 목록 출력 (단체톡 조회랑 개인톡 조회 api를 분리해도 좋을 것 같음)
    //TODO 최근 채팅, 시간 조회 + 채팅방 이름 상대방으로 적용
    @GetMapping("/chat/rooms")
    public String memberChatRoomList(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        Long memberId = userDetails.getUserId();

        //개인톡 방 출력
        model.addAttribute("pRoomInfoList", chatPersonalRoomListInfo(memberId));
        //단체톡 방 출력
        model.addAttribute("roomList", chatRoomService.findMemberChatRooms(memberId));

        return "chatroomlist";
    }

    //채팅방의 정보를 설정하는 메소드
    private List<ChatRoomResponse.ChatRoomListDTO> chatPersonalRoomListInfo(Long memberId) {
        List<ChatRoomResponse.ChatRoomListDTO> chatRoomListDTO = new ArrayList<>();
        List<ChatRoom> chatRoomList = chatRoomService.findMemberPersonalChatRooms(memberId);
        for (int i = 0; i < chatRoomList.size(); i++) {
            ChatRoomResponse.ChatRoomListDTO chatRoomInfo = new ChatRoomResponse.ChatRoomListDTO();
            ChatRoom chatRoom = chatRoomList.get(i);
            chatRoomInfo.setRoomId(chatRoom.getId());
            chatRoomInfo.setRoomTitle(chatRoom.getTitle());
            Optional<ChatMessage> message = chatMessageRepository.findTopByOrderByCreatedAtDesc(chatRoom.getId());
            chatRoomInfo.setRecentMessage(message.get().getContent());
            chatRoomInfo.setTime(message.get().getCreatedAt());
            chatRoomListDTO.add(chatRoomInfo);
        }
        return chatRoomListDTO;
    }
}