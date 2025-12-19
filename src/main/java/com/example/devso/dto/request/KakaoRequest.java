package com.example.devso.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class KakaoRequest {

    @NotBlank(message = "Auth code 는 필수 입니다.")
    private String code;
}
