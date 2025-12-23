package com.example.devso.dto.request;

import com.example.devso.entity.recruit.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class RecruitRequest {
    @NotBlank(message = "내용은 필수입니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    @Size(max = 2000, message = "내용은 2000자 이하로 작성해 주세요.")
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
