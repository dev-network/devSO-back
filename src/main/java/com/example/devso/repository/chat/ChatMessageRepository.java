package com.example.devso.repository.chat;

import com.example.devso.entity.chat.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = """
        UPDATE chat_message
        SET is_read = true
        WHERE chat_room_id = :roomId
        AND sender_id != :userId
        AND is_read = false
        """, nativeQuery = true)
    int updateReadStatus(@Param("roomId") Long roomId, @Param("userId") Long userId);

    @Query(value = "SELECT * FROM chat_message WHERE chat_room_id = :chatRoomId AND deleted_at is null ORDER BY created_at DESC",
           countQuery = "SELECT COUNT(*) FROM chat_message WHERE chat_room_id = :chatRoomId AND deleted_at is null",
           nativeQuery = true)
    Page<ChatMessage> findByChatRoomId(@Param("chatRoomId") Long chatRoomId, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query(value = """
    UPDATE chat_message SET deleted_at = :nowDate WHERE chat_room_id = :roomId
    """, nativeQuery = true)
    void deleteByRoomId(@Param("roomId") Long roomId, @Param("nowDate")LocalDateTime nowDate);
}