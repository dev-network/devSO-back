package com.example.devso.entity.recruit;

import com.example.devso.exception.CustomException;
import com.example.devso.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RecruitType {
    STUDY(1,"스터디"),
    PROJECT(2,"프로젝트");

    private final int value;
    private final String label;

    RecruitType(int value, String label) {
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
    public static RecruitType fromValue(Integer value) {
        if (value == null) return null;
        for (RecruitType method : values()) {
            if (method.value == value) {
                return method;
            }
        }
        throw new CustomException(ErrorCode.INVALID_ENUM_VALUE);
    }
}
