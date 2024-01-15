package com.devsei.userservice.application;

import com.devsei.userservice.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
}
