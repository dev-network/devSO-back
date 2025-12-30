package com.example.devso.repository;

import com.example.devso.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시물의 댓글 목록
    @Query("""
            SELECT c FROM Comment c
            JOIN FETCH c.user
            LEFT JOIN FETCH c.parentComment
            WHERE c.post.id = :postId
              AND c.deletedAt IS NULL
              AND c.post.deletedAt IS NULL
            ORDER BY c.createdAt ASC
            """)
    List<Comment> findByPostIdWithUser(@Param("postId") Long postId);

    @Query("""
            SELECT c FROM Comment c
            JOIN FETCH c.user
            WHERE c.id = :id
              AND c.deletedAt IS NULL
            """)
    Optional<Comment> findByIdWithUser(@Param("id") Long id);

    @Modifying
    @Query("""
            UPDATE Comment c
            SET c.deletedAt = :now
            WHERE c.parentComment.id = :parentId
              AND c.deletedAt IS NULL
            """)
    int softDeleteReplies(@Param("parentId") Long parentId, @Param("now") LocalDateTime now);

    // 게시물의 댓글 수
    long countByPostId(Long postId);

    long countByPostIdAndDeletedAtIsNull(Long postId);

    Optional<Comment> findByIdAndDeletedAtIsNull(Long id);

}
