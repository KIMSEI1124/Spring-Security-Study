package com.querydsl.group.member.query;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.group.member.Member;
import com.querydsl.group.member.QMember;
import com.querydsl.group.team.QTeam;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.util.StringUtils;

import java.util.List;

public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Member> findMembers() {
        return jpaQueryFactory
                .selectFrom(QMember.member)
                .fetch();
    }

    @Override
    public Member findByMemberId(Long id) {
        return jpaQueryFactory
                .selectFrom(QMember.member)
                .where(QMember.member.id.eq(id))
                .fetchOne();
    }

    @Override
    public List<Member> findByTeamName(String name) {
        return jpaQueryFactory
                .selectFrom(QMember.member)
                .join(QMember.member.team, QTeam.team).fetchJoin()
                .where(QTeam.team.name.eq(name))
                .fetch();
    }

    @Override
    public List<Member> findByTeamNameV2(String name) {
        return jpaQueryFactory
                .selectFrom(QMember.member)
                .join(QMember.member.team, QTeam.team).fetchJoin()
                .where(hasName(name))
                .fetch();
    }

    private BooleanExpression hasName(String name) {
        return StringUtils.hasText(name) ? QTeam.team.name.eq(name) : null;
    }
}
