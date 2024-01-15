package com.devsei.userservice.application;

import com.devsei.userservice.domain.UserJpaEntity;
import com.devsei.userservice.dto.UserCreateReq;
import com.devsei.userservice.dto.UserCreateRes;
import com.devsei.userservice.dto.UserFindRes;

public interface UserService {
    UserCreateRes createUser(UserCreateReq req);

    UserFindRes getUserByUserId(String userId);

    Iterable<UserJpaEntity> getUserByAll();
}
