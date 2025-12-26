package com.example.devso.entity.recruit;

import com.example.devso.dto.request.recruit.RecruitRequest;
import com.example.devso.entity.BaseEntity;
import com.example.devso.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "recruit_comments")
@SQLDelete(sql = "UPDATE recruit_comments SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
@Getter
@NoArgsConstructor
public class RecruitComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_id", nullable = false)
    private Recruit recruit;

    // ===== 생성 =====
    public static RecruitComment create(String content, User user, Recruit recruit) {
        RecruitComment comment = new RecruitComment();
        comment.content = content;
        comment.user = user;
        comment.recruit = recruit;
        return comment;
    }

    // ===== 수정 =====
    public void update(String content) {
        this.content = content;
    }
}

