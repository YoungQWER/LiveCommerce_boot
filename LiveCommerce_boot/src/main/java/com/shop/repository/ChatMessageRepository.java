package com.shop.repository;

import org.aspectj.bridge.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<Message, String> {
}
