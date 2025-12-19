package com.example.devso.service.chat;


import com.example.devso.entity.chat.ChatMessage;
import com.example.devso.entity.chat.ChatRoom;
import com.example.devso.entity.chat.ChatRoomMember;
import com.example.devso.repository.chat.ChatMessageRepository;
import com.example.devso.repository.chat.ChatRoomMemberRepository;
import com.example.devso.repository.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final ChatMessageRepository chatMessageRepository;

    /**
     * 1:1 채팅방 생성 (이미 있다면 기존 방 ID 반환)
     */
    @Transactional
    public Long createOrGetRoom(Long myId, Long opponentId) {
        return chatRoomMemberRepository.findRoomIdByUsers(myId, opponentId)
                .orElseGet(() -> {
                    // 1. 방 생성
                    ChatRoom room = new ChatRoom();
                    chatRoomRepository.save(room);

                    // 2. 멤버 등록 (나)
                    ChatRoomMember me = ChatRoomMember.builder()
                            .chatRoom(room).userId(myId).build();
                    // 3. 멤버 등록 (상대방)
                    ChatRoomMember opponent = ChatRoomMember.builder()
                            .chatRoom(room).userId(opponentId).build();

                    chatRoomMemberRepository.saveAll(List.of(me, opponent));
                    return room.getId();
                });
    }

    /**
     * 메시지 저장
     */
    @Transactional
    public ChatMessage saveMessage(Long roomId, Long senderId, String text) {
        ChatRoom room = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("방이 존재하지 않습니다."));

        ChatMessage message = ChatMessage.builder()
                .chatRoom(room)
                .senderId(senderId)
                .message(text)
                .isRead(false)
                .build();

        return chatMessageRepository.save(message);
    }

    /**
     * 채팅방 입장 시 읽음 처리
     */
    @Transactional
    public void markAsRead(Long roomId, Long userId) {
        chatMessageRepository.updateReadStatus(roomId, userId);
    }
}