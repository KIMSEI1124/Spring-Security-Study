package com.devsei.userservice.vo;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class UserVo {
    private String email;
    private String name;
    private String password;
    private String userId;
    private Date createdAt;
    private String encryptedPassword;

    public void generatedUserId() {
        this.userId = UUID.randomUUID().toString();
    }
}
