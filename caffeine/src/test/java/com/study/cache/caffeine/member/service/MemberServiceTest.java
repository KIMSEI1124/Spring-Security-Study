package com.study.cache.caffeine.member.service;

import com.study.cache.caffeine.member.domain.Member;
import com.study.cache.caffeine.member.domain.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class MemberServiceTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("카페인 캐시 조회를 테스트한다.")
    @Test
    void cacheTest() {
        /* Given */
        Member saveMember = memberRepository.save(Member.builder()
                .name("카페인")
                .build());

        /* When */
        log.info("Search By Repository");
        Member findMember = memberService.findById(saveMember.getId());

        log.info("Search By Cache");
        Member findMemberWithCache = memberService.findById(saveMember.getId());

        /* Then */
        assertThat(findMember).isEqualTo(findMemberWithCache);
    }
}