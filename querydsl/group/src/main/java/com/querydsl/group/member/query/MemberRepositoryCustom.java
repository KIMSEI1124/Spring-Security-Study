package com.querydsl.group.member.query;

import com.querydsl.group.member.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findMembers();

    Member findByMemberId(Long id);

    List<Member> findByTeamName(String name);

    List<Member> findByTeamNameV2(String name);
}
