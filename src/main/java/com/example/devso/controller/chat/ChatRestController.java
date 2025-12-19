package com.example.devso.controller.chat;

import com.example.devso.Security.CustomUserDetails;
import com.example.devso.repository.chat.ChatRoomRepository;
import com.example.devso.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatRestController {

    private final ChatService chatService;
    private final ChatRoomRepository chatRoomRepository;

    // 내 채팅 목록 가져오기
    @GetMapping("/rooms")
    public ResponseEntity<?> getMyRooms(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(chatRoomRepository.findAllChatRoomsByUserId(userDetails.getId()));
    }

    // 채팅방 생성 또는 입장
    @PostMapping("/rooms/{opponentId}")
    public ResponseEntity<?> enterRoom(@AuthenticationPrincipal CustomUserDetails userDetails,
                                       @PathVariable Long opponentId) {
        Long roomId = chatService.createOrGetRoom(userDetails.getId(), opponentId);
        return ResponseEntity.ok(Map.of("roomId", roomId));
    }
}
