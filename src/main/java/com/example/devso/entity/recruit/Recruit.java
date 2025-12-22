package com.example.devso.entity.recruit;

import com.example.devso.entity.*;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recruits")
@Getter
@NoArgsConstructor
public class Recruit extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RecruitType type; // STUDY, PROJECT

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 2000)
    private String content;

    private int totalCount;
    private int currentCount;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private RecruitStatus status; // OPEN, CLOSED

    @Enumerated(EnumType.STRING)
    private RecruitPosition position; // FRONTEND, BACKEND, FULLSTACK

    @Enumerated(EnumType.STRING)
    private RecruitProgressType progressType; // ONLINE, OFFLINE, HYBRID

    @OneToMany(mappedBy = "recruit", cascade = CascadeType.REMOVE)
    private List<RecruitComment> recruitComments = new ArrayList<>();

    @OneToMany(mappedBy = "recruit", cascade = CascadeType.REMOVE)
    private List<RecruitBookMark> recruitBookMarks = new ArrayList<>();

    @Column(nullable = false)
    private LocalDate deadLine;

    @ElementCollection(targetClass = TechStack.class)
    @CollectionTable(name = "recruit_stacks", joinColumns = @JoinColumn(name = "recruit_id"))
    @Column(name = "stack")
    @Enumerated(EnumType.STRING)
    private List<TechStack> stacks = new ArrayList<>();

    @Column(nullable = false)
    private long viewCount = 0;

    public void increaseViewCount() {
        this.viewCount++;
    }

    @Builder
    public Recruit(RecruitType type, String title, String content, int totalCount, int currentCount, String imageUrl, User user, RecruitStatus status, RecruitPosition position, RecruitProgressType progressType, List<RecruitComment> recruitComments, List<RecruitBookMark> recruitBookMarks, List<TechStack> stacks) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.totalCount = totalCount;
        this.currentCount = currentCount;
        this.imageUrl = imageUrl;
        this.user = user;
        this.status = status;
        this.position = position;
        this.progressType = progressType;
        this.recruitComments = recruitComments;
        this.recruitBookMarks = recruitBookMarks;
        this.stacks = stacks;
    }
}
