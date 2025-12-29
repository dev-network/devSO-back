package com.example.devso.dto.request.recruit;

import com.example.devso.entity.recruit.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class RecruitRequest {
    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    @NotNull(message = "모집 유형(스터디/프로젝트)을 선택해주세요.")
    private RecruitType type;

    @NotNull(message = "진행 방식을 선택해주세요.") // 메시지 추가 권장
    private RecruitProgressType progressType;

    @NotEmpty(message = "최소 하나 이상의 기술 스택을 선택해주세요.") // 메시지 추가 권장
    private List<TechStack> stacks;

    @NotNull(message = "모집 인원을 입력해주세요.")
    @Min(value = 1, message = "모집 인원은 최소 1명 이상이어야 합니다.")
    private Integer totalCount;

    @NotNull(message = "마감일을 선택해주세요.")
//    @FutureOrPresent(message = "마감일은 오늘 이후여야 합니다.")
    private LocalDate deadLine;

    @NotEmpty(message = "최소 하나 이상의 포지션을 선택해주세요.")
    private List<RecruitPosition> positions;

    @NotNull(message = "예상 기간을 선택해주세요.")
    private RecruitDuration duration;

    @NotNull(message = "연락 방법을 선택해주세요.")
    private RecruitContactMethod contactMethod;

    private String contactInfo;
}
