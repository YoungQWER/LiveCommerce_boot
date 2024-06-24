package com.shop.controller;

import com.shop.dto.MessageDto;
import com.shop.entity.Member;
import com.shop.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class ChatController {

    @Autowired
    private MemberService memberService;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public MessageDto sendMessage(@Payload MessageDto message) {
        return message;
    }

    @GetMapping("/chat")
    public String getChatPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Member member = memberService.findByEmail(email);
        if (member != null) {
            model.addAttribute("currentUserName", member.getName());
        } else {
            model.addAttribute("currentUserName", "Unknown User");
        }

        log.info("--------------------email--------" , email);
        log.info("currentUserName");

        return "chat";
    }
}