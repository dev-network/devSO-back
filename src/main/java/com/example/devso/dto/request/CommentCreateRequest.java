package com.example.devso.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreateRequest {
    @NotBlank(message = "댓글 내용을 입력해주세요.")
    @Size(max = 100, message = "댓글은 100자 이하여야 합니다.")
    private String content;

    // 대댓글인 경우에만 설정 (댓글 depth는 2까지만 허용)
    private Long parentCommentId;

    // @멘션 대상 유저 id 목록 (유저 참조 형태로 저장)
    private java.util.List<Long> mentionedUserIds;
}
