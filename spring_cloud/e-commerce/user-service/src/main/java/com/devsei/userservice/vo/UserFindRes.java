package com.devsei.userservice.vo;

import com.devsei.userservice.domain.UserJpaEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserFindRes {
    private String name;
    private String email;
    private String userId;

    private List<OrderRes> orders;

    public static UserFindRes of(UserJpaEntity user, List<OrderRes> orders) {
        return UserFindRes.builder()
                .name(user.getName())
                .email(user.getEmail())
                .userId(user.getUserId())
                .orders(orders)
                .build();
    }
}
