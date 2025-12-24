package com.example.devso.entity.recruit;

import com.example.devso.exception.CustomException;
import com.example.devso.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RecruitContactMethod {
    OPEN_TALK(1, "오픈 톡"),
    EMAIL(2, "이메일"),
    GOOGLE_FORM(3, "구글 폼"),
    OTHER(0, "기타");

    private final int value;
    private final String label;

    RecruitContactMethod(int value, String label) {
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
    public static RecruitContactMethod fromValue(int value) {
        for (RecruitContactMethod method : values()) {
            if (method.value == value) {
                return method;
            }
        }
        throw new CustomException(ErrorCode.INVALID_ENUM_VALUE);
    }
}
