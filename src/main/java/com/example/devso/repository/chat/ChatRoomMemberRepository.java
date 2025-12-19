package com.example.devso.repository.chat;

import com.example.devso.entity.chat.ChatRoomMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomMemberRepository extends JpaRepository<ChatRoomMember, Long> {
    @Query(value = """
        SELECT m1.chat_room_id 
        FROM chat_room_member m1
        JOIN chat_room_member m2 ON m1.chat_room_id = m2.chat_room_id
        WHERE m1.user_id = :user1Id AND m2.user_id = :user2Id
        LIMIT 1
        """, nativeQuery = true)
    Optional<Long> findRoomIdByUsers(@Param("user1Id") Long user1Id, @Param("user2Id") Long user2Id);

}
