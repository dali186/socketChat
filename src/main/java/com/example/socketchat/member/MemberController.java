package com.example.socketchat.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public String allMembers(Model model) {
        model.addAttribute("memberList", memberService.findAllMembers());
        return "memberlist";
    }
}
