package com.study.module.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<com.study.module.member.Member, Integer> {
}
