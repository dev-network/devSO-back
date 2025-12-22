package com.example.devso.entity.recruit;

import com.example.devso.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recruit_comments")
@Getter
@NoArgsConstructor
public class RecruitComment {
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

    @Builder
    public RecruitComment(String content, User user, Recruit recruit) {
        this.content = content;
        this.user = user;
        this.recruit = recruit;
    }
}

