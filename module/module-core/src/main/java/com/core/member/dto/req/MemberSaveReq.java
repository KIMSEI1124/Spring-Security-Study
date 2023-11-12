package com.core.member.dto.req;

import com.data.member.Member;
import com.data.team.Team;

public record MemberSaveReq(String name,
                            int age,
                            int teamId) {
    public Member toEntity(Team team) {
        return Member.of(name, age, team);
    }
}
