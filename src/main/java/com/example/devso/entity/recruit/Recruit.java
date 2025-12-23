package com.example.devso.entity.recruit;

import com.example.devso.dto.request.RecruitRequest;
import com.example.devso.entity.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recruits")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

//    @Builder
//    public Recruit(RecruitType type, String title, String content, int totalCount, int currentCount, String imageUrl, User user, RecruitStatus status, RecruitPosition position, RecruitProgressType progressType, List<RecruitComment> recruitComments, List<RecruitBookMark> recruitBookMarks, List<TechStack> stacks) {
//        this.type = type;
//        this.title = title;
//        this.content = content;
//        this.totalCount = totalCount;
//        this.currentCount = currentCount;
//        this.imageUrl = imageUrl;
//        this.user = user;
//        this.status = status;
//        this.position = position;
//        this.progressType = progressType;
//        this.recruitComments = recruitComments;
//        this.recruitBookMarks = recruitBookMarks;
//        this.stacks = stacks;
//    }

    // 생성
    public static Recruit create(User user, RecruitRequest req) {
        Recruit recruit = new Recruit();
        recruit.user = user;
        recruit.title = req.getTitle();
        recruit.content = req.getContent();
        recruit.type = req.getType();
        recruit.position = req.getPosition();
        recruit.progressType = req.getProgressType();
        recruit.stacks = req.getStacks();
        recruit.totalCount = req.getTotalCount();
        recruit.currentCount = 0;
        recruit.status = RecruitStatus.OPEN;
        recruit.deadLine = req.getDeadLine();
        recruit.imageUrl = req.getImageUrl();
        return recruit;
    }

    //수정
    public void update(
            String title,
            String content,
            RecruitPosition position,
            RecruitProgressType progressType,
            List<TechStack> stacks,
            int totalCount,
            LocalDate deadLine,
            String imageUrl
    ) {
        this.title = title;
        this.content = content;
        this.position = position;
        this.progressType = progressType;
        this.stacks = stacks;
        this.totalCount = totalCount;
        this.deadLine = deadLine;
        this.imageUrl = imageUrl;
    }

    // 조회수 증가
    public void increaseViewCount() {
        this.viewCount++;
    }

    // 모집 인원 증가
    public void increaseCurrentCount() {
        if (currentCount >= totalCount) {
            throw new IllegalStateException("모집 인원 초과");
        }
        currentCount++;
    }

    // 작성자 검증
    public boolean isOwner(Long userId) {
        return this.user.getId().equals(userId);
    }

    // 모집 마감
    public void close() {
        this.status = RecruitStatus.CLOSED;
    }

}
