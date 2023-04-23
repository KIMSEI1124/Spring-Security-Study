package com.querydsl.group.member.query;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.group.member.Member;
import com.querydsl.group.member.QMemberDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.querydsl.group.member.QMember.member;
import static com.querydsl.group.team.QTeam.team;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory query;
    private final EntityManager em;

    @Override
    public List<Member> findMembers() {
        // DTO
        query
                .select(new QMemberDto(
                        member.name,
                        team.name
                )).from(member)
                .join(member.team, team).fetchJoin()
                .fetch();
        return query
                .select(member)
                .from(member)
                .fetch();
    }

    @Override
    public Member findByMemberId(Long id) {
        return query
                .selectFrom(member)
                .where(member.id.eq(id))
                .fetchOne();
    }

    @Override
    public List<Member> findByTeamName(String name) {
        return query
                .selectFrom(member)
                .join(member.team, team).fetchJoin()
                .where(team.name.eq(name))
                .fetch();
    }

    @Override
    public List<Member> findByTeamNameV2(String name) {
        return query
                .selectFrom(member)
                .join(member.team, team).fetchJoin()
                .where(hasName(name))
                .fetch();
    }

    private BooleanExpression hasName(String name) {
        return StringUtils.hasText(name) ? team.name.eq(name) : null;
    }
}
