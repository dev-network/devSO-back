package com.example.devso.entity.recruit;

import com.example.devso.exception.CustomException;
import com.example.devso.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RecruitPosition {
    ALL(0, "전체"),
    BACKEND(1,"백엔드"),
    FRONTEND(2,"프론트엔드"),
    DESIGNER(3,"디자이너"),
    IOS(4,"iOS"),
    ANDROID(5,"안드로이드"),
    DEVOPS(6,"데브옵스"),
    PM(7,"PM"),
    PLANNER(8,"기획자"),
    MARKETER(9,"마케터");

    private final int value;
    private final String label;

    RecruitPosition(int value, String label) {
        this.value = value;
        this.label = label;
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    // value로 enum 찾기
    @JsonCreator
    public static RecruitPosition fromValue(Integer value) {
        if (value == null) return null;
        for (RecruitPosition method : values()) {
            if (method.value == value) {
                return method;
            }
        }
        throw new CustomException(ErrorCode.INVALID_ENUM_VALUE);
    }
}
