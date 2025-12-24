package com.example.devso.repository.chat;

import com.example.devso.entity.chat.ChatRoomMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ChatRoomMemberRepository extends JpaRepository<ChatRoomMember, Long> {
    @Query(value = """
        SELECT chat_room_id
        FROM chat_room_member
        WHERE user_id IN (:user1Id, :user2Id)
        AND deleted_at is null
        GROUP BY chat_room_id
        HAVING COUNT(DISTINCT user_id) = 2
        LIMIT 1
        """, nativeQuery = true)
    Optional<Long> findRoomIdByUsers(@Param("user1Id") Long user1Id, @Param("user2Id") Long user2Id);

    @Query(value = "SELECT EXISTS(SELECT 1 FROM chat_room_member WHERE chat_room_id = :chatRoomId AND user_id = :userId AND deleted_at is null)", nativeQuery = true)
    long existsByChatRoomIdAndUserId(@Param("chatRoomId") Long chatRoomId, @Param("userId") Long userId);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE chat_room_member SET deleted_at = :nowDate WHERE chat_room_id = :roomId AND user_id = :userId", nativeQuery = true)
    void deleteByChatRoomIdAndUserId(@Param("roomId") Long roomId, @Param("userId") Long userId, @Param("nowDate")LocalDateTime nowDate);

    @Query(value = "SELECT COUNT(*) FROM chat_room_member WHERE chat_room_id = :roomId AND deleted_at is null", nativeQuery = true)
    int countByChatRoomId(@Param("roomId") Long roomId);
}
