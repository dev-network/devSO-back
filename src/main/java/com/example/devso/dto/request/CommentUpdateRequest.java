package com.example.devso.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentUpdateRequest {
    @NotBlank(message = "댓글 내용을 입력해주세요.")
    @Size(max = 100, message = "댓글은 100자 이하여야 합니다.")
    private String content;

    // null이면 기존 멘션 유지, 빈 배열이면 멘션 제거
    private java.util.List<Long> mentionedUserIds;
}


