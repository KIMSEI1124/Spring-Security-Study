package com.study.module.dto.req;

import com.study.module.member.Member;
import com.study.module.team.Team;

public record MemberSaveReq(String name,
                            int age,
                            int teamId) {
    public Member toEntity(Team team) {
        return Member.of(name, age, team);
    }
}
