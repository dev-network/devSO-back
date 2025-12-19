package com.example.devso.dto.request;


import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequest {


    // email로 회원가입 처리할지
    @NotBlank(message = "사용자명은 필수입니다.")
    @Size(min= 3, max = 20, message = "사용자명은 3 ~ 20자 입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입니다.")
    @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
    private String password;

    @NotBlank(message = "이름은 필수 입니다.")
    private String name;


    @NotBlank(message = "전화번호는 필수입니다.")
    @Size(max = 30, message = "전화번호는 30자를 초과할 수 없습니다.")
    private String phone;

}