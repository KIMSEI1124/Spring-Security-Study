package com.querydsl.group.member;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.querydsl.group.member.QMemberDto is a Querydsl Projection type for MemberDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMemberDto extends ConstructorExpression<MemberDto> {

    private static final long serialVersionUID = -486347860L;

    public QMemberDto(com.querydsl.core.types.Expression<String> memberName, com.querydsl.core.types.Expression<String> teamName) {
        super(MemberDto.class, new Class<?>[]{String.class, String.class}, memberName, teamName);
    }

}

