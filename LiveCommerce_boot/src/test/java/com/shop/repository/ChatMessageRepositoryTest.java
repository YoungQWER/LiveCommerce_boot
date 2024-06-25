//package com.shop.repository;
//
//import com.shop.entity.ChatMessage;
//import com.shop.entity.Member;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest
//public class ChatMessageRepositoryTest {
//
//    @Autowired
//    private ChatMessageRepository chatMessageRepository;
//
//    @Test
//    public void saveChatMessageTest() {
//        // Given
//        Member member = new Member();
//        member.setEmail("test@example.com");
//        member.setName("Test User");
//
//        ChatMessage message = new ChatMessage();
//        message.setMessage("Hello, World!");
//        message.setMember(member);
//
//        // When
//        chatMessageRepository.save(message);
//
//        // Then
//        assertNotNull(message.getId());
//        assertNotNull(message.getRegTime());
//    }
//}
