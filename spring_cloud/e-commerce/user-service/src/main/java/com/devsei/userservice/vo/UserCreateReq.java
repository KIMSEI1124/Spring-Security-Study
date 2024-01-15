package com.devsei.userservice.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreateReq(
        @NotNull(message = "Email cannot be null")
        @Size(min = 2, message = "Email to be less then two characters")
        @Email
        String email,
        @NotNull(message = "Name cannot be null")
        @Size(min = 2, message = "Name to be less then two characters")
        String name,
        @NotNull(message = "Password cannot be null")
        @Size(min = 8, message = "Password must be equal or grater than 8 characters")
        String password
) {
}
