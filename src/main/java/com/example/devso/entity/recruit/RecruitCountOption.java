package com.example.devso.entity.recruit;

import com.example.devso.exception.CustomException;
import com.example.devso.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RecruitCountOption {
    ONE(1, "1명"),
    TWO(2, "2명"),
    THREE(3, "3명"),
    FOUR(4, "4명"),
    FIVE(5, "5명"),
    SIX(6, "6명"),
    SEVEN(7, "7명"),
    EIGHT(8, "8명"),
    NINE(9, "9명"),
    TEN(10, "10명 이상");

    private final int value;
    private final String label;

    RecruitCountOption(int value, String label) {
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
    public static RecruitCountOption fromValue(int value) {
        for (RecruitCountOption method : values()) {
            if (method.value == value) {
                return method;
            }
        }
        throw new CustomException(ErrorCode.INVALID_ENUM_VALUE);
    }
}
