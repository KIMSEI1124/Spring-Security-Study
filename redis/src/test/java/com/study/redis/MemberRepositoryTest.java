package com.study.redis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    private Member member;

    @AfterEach
    void delete() {
        memberRepository.delete(member);    // (4)
    }

    @DisplayName("Redis 에 데이터를 저장한다.")
    @Test
    void test() {
        member = Member.builder()
                .name("홍길동")
                .age(20)
                .build();
        memberRepository.save(member);  // (1)

        String memberId = member.getId();
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(RuntimeException::new);    // (2)

        assertThat(findMember.getId()).isEqualTo(member.getId());   // (3)
    }
}