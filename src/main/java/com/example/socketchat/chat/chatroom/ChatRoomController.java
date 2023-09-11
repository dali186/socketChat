package com.example.socketchat.chat.chatroom;

import com.example.socketchat.chat.chatmessage.ChatMessageRepository;
import com.example.socketchat.member.MemberService;
import com.example.socketchat.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ChatMessageRepository chatMessageRepository;
    private final MemberService memberService;

    //1:1 채팅방 생성
    //TODO 중복처리
    @GetMapping("/chat/open/{memberId}")
    public String openRoom(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long memberId) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.getMemberList().add(userDetails.getUserId());
        chatRoom.getMemberList().add(memberId);

        chatRoom.setTitle(memberService.findMemberById(memberId).get().getUsername());

        String roomId = chatRoomService.openChatRoom(chatRoom);

        return "redirect:/chat/room/" + roomId;
    }

    //채팅방 목록 출력
    @GetMapping("/chat/rooms")
    public String memberChatRoomList(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        Long memberId = userDetails.getUserId();

        model.addAttribute("roomList", chatRoomService.findMemberChatRooms(memberId));

        return "chatroomlist";
    }
}