package com.example.devso.repository.chat;

import com.example.devso.entity.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

        @Query(value = """
        SELECT 
            cr.id AS roomId, 
            cm_last.message AS lastMessage, 
            cm_last.created_at AS lastMessageTime,
            (SELECT COUNT(*) FROM chat_message cm 
             WHERE cm.chat_room_id = cr.id 
             AND cm.sender_id != :userId 
             AND cm.is_read = false) AS unreadCount,
            other_m.user_id AS opponentId
        FROM chat_room cr
        JOIN chat_room_member my_m ON cr.id = my_m.chat_room_id AND my_m.user_id = :userId
        JOIN chat_room_member other_m ON cr.id = other_m.chat_room_id AND other_m.user_id != :userId
        LEFT JOIN (
            SELECT chat_room_id, message, created_at
            FROM chat_message
            WHERE (chat_room_id, created_at) IN (
                SELECT chat_room_id, MAX(created_at)
                FROM chat_message GROUP BY chat_room_id
            )
        ) cm_last ON cr.id = cm_last.chat_room_id
        ORDER BY cm_last.created_at DESC
        """, nativeQuery = true)
        List<ChatRoom> findAllChatRoomsByUserId(@Param("userId") Long userId);
    }
