package com.example.devso.dto.request;

import com.example.devso.entity.recruit.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private RecruitPosition position;

    @NotNull
    private RecruitProgressType progressType;

    @NotNull
    private RecruitStatus status;

    @NotNull
    private List<TechStack> stacks;

    @NotNull
    private Integer totalCount;

    private String imageUrl;
}
