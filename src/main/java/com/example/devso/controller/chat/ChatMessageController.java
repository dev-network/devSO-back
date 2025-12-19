package com.example.devso.controller.chat;

import com.example.devso.dto.request.chat.ChatMessageRequest;
import com.example.devso.dto.response.chat.ChatMessageResponse;
import com.example.devso.entity.chat.ChatMessage;
import com.example.devso.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    @MessageMapping("/chat/send")
    public void sendMessage(@Payload ChatMessageRequest request, Principal principal) {
        Long senderId = Long.parseLong(principal.getName()); // 인증된 사용자 ID

        // 1. DB에 메시지 저장
        ChatMessage savedMsg = chatService.saveMessage(
                request.getRoomId(),
                senderId,
                request.getMessage()
        );

        // 2. 메시지 정보 구성 (DTO)
        ChatMessageResponse response = ChatMessageResponse.of(savedMsg);

        // 3. /topic/room.{roomId} 를 구독 중인 사람들에게 전송 (브로드캐스트)
        // Redis가 없으므로 In-Memory 브로커가 처리함
        messagingTemplate.convertAndSend("/topic/room." + request.getRoomId(), response);
    }
}