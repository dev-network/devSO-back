package com.example.devso.entity.recruit;

import com.example.devso.exception.CustomException;
import com.example.devso.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RecruitDuration {
    ONE_MONTH(1, "1개월"),
    TWO_MONTHS(2, "2개월"),
    THREE_MONTHS(3, "3개월"),
    FOUR_MONTHS(4, "4개월"),
    FIVE_MONTHS(5, "5개월"),
    SIX_MONTHS(6, "6개월"),
    LONG_TERM(0, "장기");

    private final int value;
    private final String label;

    RecruitDuration(int value, String label) {
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
    public static RecruitDuration fromValue(int value) {
        for (RecruitDuration method : values()) {
            if (method.value == value) {
                return method;
            }
        }
        throw new CustomException(ErrorCode.INVALID_ENUM_VALUE);
    }
}
