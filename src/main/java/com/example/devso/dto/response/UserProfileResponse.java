package com.example.devso.dto.response;

import com.example.devso.entity.User;
import com.example.devso.entity.Role;


import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class UserProfileResponse {

    private Long id;
    private String username;
    private String name;
    private Role role;
    private String phone;
    private String status;



    public static UserProfileResponse of(User user) {
        return UserProfileResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .role(user.getRole())
                .phone(user.getPhone())
                .build();
    }
}