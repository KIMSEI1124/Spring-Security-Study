package com.devsei.userservice.application;

import com.devsei.userservice.domain.UserJpaEntity;
import com.devsei.userservice.dto.UserDto;
import com.devsei.userservice.vo.UserCreateReq;
import com.devsei.userservice.vo.UserCreateRes;
import com.devsei.userservice.vo.UserFindRes;

public interface UserService {
    UserCreateRes createUser(UserCreateReq req);

    UserFindRes getUserByUserId(String userId);

    Iterable<UserJpaEntity> getUserByAll();
}
