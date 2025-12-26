package com.example.devso.dto.response.recruit;

import com.example.devso.dto.response.UserResponse;
import com.example.devso.entity.recruit.RecruitComment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class RecruitCommentResponse {
    private Long id;
    private String content;
    private UserResponse author;
    private LocalDateTime createdAt;
    @JsonProperty("isOwner")
    private boolean isOwner;

    public static RecruitCommentResponse from(RecruitComment comment, Long currentUserId) {
        return RecruitCommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .author(UserResponse.from(comment.getUser()))
                .createdAt(comment.getCreatedAt())
                .isOwner(comment.getUser().getId().equals(currentUserId))
                .build();
    }
}