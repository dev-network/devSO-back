package com.example.devso.dto.response.recruit;

import com.example.devso.entity.recruit.TechStack;
import lombok.Builder;
import lombok.Getter;

//프론트엔드에 기술 스택의 상세 정보(카테고리, 아이콘)를 전달하기 위한 내부 클래스
@Getter
public class StackResponse extends EnumResponse {
    private String category;
    private String imageUrl;

    @Builder // 수동 생성자에 빌더 적용
    public StackResponse(int value, String label, String key, String category, String imageUrl) {
        super(value, label, key);
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public static StackResponse from(TechStack stack) {
        return StackResponse.builder()
                .value(stack.getValue())
                .label(stack.getLabel())
                .category(stack.getCategory())
                .imageUrl(stack.getImageUrl())
                .build();
    }
}
