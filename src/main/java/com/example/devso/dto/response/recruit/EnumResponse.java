package com.example.devso.dto.response.recruit;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EnumResponse {
    private int value;    // 숫자 (예: 1)
    private String label;  // 한글 (예: "온라인")
    private String key;    // 영문 상수명 (예: "ONLINE")
}
