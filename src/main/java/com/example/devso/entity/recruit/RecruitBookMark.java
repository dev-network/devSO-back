package com.example.devso.entity.recruit;

import com.example.devso.entity.BaseEntity;
import com.example.devso.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recruit_bookmarks", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "recruit_id"}) // 동일 사용자의 중복 북마크 방지
})
@Getter
@NoArgsConstructor
public class RecruitBookMark extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_id", nullable = false)
    private Recruit recruit;

    @Builder
    public RecruitBookMark(User user, Recruit recruit) {
        this.user = user;
        this.recruit = recruit;
    }
}

