package com.example.devso.dto.response.recruit;

import com.example.devso.dto.request.recruit.RecruitCommentRequest;
import com.example.devso.dto.response.UserResponse;
import com.example.devso.entity.recruit.RecruitComment;
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

    public static RecruitCommentResponse from(RecruitComment comment) {
        return RecruitCommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .author(UserResponse.from(comment.getUser()))
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
