package com.example.devso.entity.recruit;

import com.example.devso.entity.*;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recruits")
@Getter
@NoArgsConstructor
public class Recruit {
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

    @ElementCollection
    @CollectionTable(name = "recruit_stacks")
    @Column(name = "stack")
    private List<String> stacks = new ArrayList<>();

    @Builder
    public Recruit(RecruitProgressType progressType, RecruitPosition position, RecruitStatus status, User user, String imageUrl, String content, String title, RecruitType type) {
        this.progressType = progressType;
        this.position = position;
        this.status = status;
        this.user = user;
        this.imageUrl = imageUrl;
        this.content = content;
        this.title = title;
        this.type = type;
    }
}
