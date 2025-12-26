package com.example.devso.repository.recruit;

import com.example.devso.entity.Comment;
import com.example.devso.entity.recruit.RecruitComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecruitCommentRepository extends JpaRepository<RecruitComment, Long> {
    // 특정 게시물의 댓글 목록
    @Query("SELECT c FROM RecruitComment c JOIN FETCH c.user WHERE c.recruit.id = :recruitId")
    List<RecruitComment> findByRecruitIdWithUser(@Param("recruitId") Long recruitId);

    // 게시물의 댓글 수
    long countByRecruitId(Long recruitId);
}
