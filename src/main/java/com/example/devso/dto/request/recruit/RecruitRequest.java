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
    @NotBlank(message = "제목은 필수입니다.") // 메시지 수정 (기존엔 제목인데 내용으로 되어있었음)
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    @NotNull
    private RecruitType type;

    @NotNull
    private RecruitProgressType progressType;

    @NotEmpty
    private List<TechStack> stacks;

    @Min(1)
    private Integer totalCount;

    private String imageUrl;

    @NotNull
    private LocalDate deadLine;

    @NotEmpty
    private List<RecruitPosition> positions;

    @NotNull
    private RecruitDuration duration;

    @NotNull
    private RecruitContactMethod contactMethod;

    private String contactInfo;
}
