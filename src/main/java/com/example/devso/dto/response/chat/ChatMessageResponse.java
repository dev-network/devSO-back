package com.example.devso.dto.response.chat;

import com.example.devso.dto.response.UserResponse;
import com.example.devso.entity.User;
import com.example.devso.entity.chat.ChatMessage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class ChatMessageResponse {


    private Long roomId;
    private Long senderId;
    private String message;

    public static ChatMessageResponse of(ChatMessage savedMsg) {
        return ChatMessageResponse.builder()
                .roomId(savedMsg.getChatRoom().getId())
                .senderId(savedMsg.getSenderId())
                .message(savedMsg.getMessage())
                .build();
    }
}
