package com.devsei.userservice.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserCreateRes {
    private String email;
    private String name;
    private String userId;
}
