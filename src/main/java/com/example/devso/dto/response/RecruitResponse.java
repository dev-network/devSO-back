package com.example.devso.dto.response;

import com.example.devso.entity.recruit.Recruit;
import com.example.devso.entity.recruit.TechStack;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class RecruitResponse {
    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private int totalCount;
    private int currentCount;
    private String type;
    private String status;
    private String position;
    private String progressType;
    private List<TechStack> stacks;
    private String username;

    private boolean bookmarked;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDate deadLine;
    private long viewCount;

    public void increaseViewCount() {
        this.viewCount++;
    }

    public static RecruitResponse from(Recruit recruit){
        return RecruitResponse.builder()
                .id(recruit.getId())
                .title(recruit.getTitle())
                .content(recruit.getContent())
                .imageUrl(recruit.getImageUrl())
                .totalCount(recruit.getTotalCount())
                .currentCount(recruit.getCurrentCount())
                .type(recruit.getType().name())
                .status(recruit.getStatus().name())
                .position(recruit.getPosition().name())
                .progressType(recruit.getProgressType().name())
                .stacks(recruit.getStacks())
                .createdAt(recruit.getCreatedAt())
                .updatedAt(recruit.getUpdatedAt())
                .username(recruit.getUser().getUsername())
                .deadLine(recruit.getDeadLine())
                .bookmarked(false)
                .viewCount(0)
                .build();
    }

    public static RecruitResponse from(Recruit recruit, boolean bookmarked){
        return RecruitResponse.builder()
                .id(recruit.getId())
                .title(recruit.getTitle())
                .content(recruit.getContent())
                .imageUrl(recruit.getImageUrl())
                .totalCount(recruit.getTotalCount())
                .currentCount(recruit.getCurrentCount())
                .type(recruit.getType().name())
                .status(recruit.getStatus().name())
                .position(recruit.getPosition().name())
                .progressType(recruit.getProgressType().name())
                .stacks(recruit.getStacks())
                .createdAt(recruit.getCreatedAt())
                .updatedAt(recruit.getUpdatedAt())
                .username(recruit.getUser().getUsername())
                .deadLine(recruit.getDeadLine())
                .bookmarked(bookmarked)
                .viewCount(recruit.getViewCount())
                .build();
    }
}

