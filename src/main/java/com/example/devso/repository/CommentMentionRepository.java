package com.example.devso.repository;

import com.example.devso.entity.CommentMention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentMentionRepository extends JpaRepository<CommentMention, Long> {
    @Modifying
    @Query("DELETE FROM CommentMention m WHERE m.comment.id = :commentId")
    int deleteByCommentId(@Param("commentId") Long commentId);
}


