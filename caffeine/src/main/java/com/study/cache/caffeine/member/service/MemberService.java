package com.study.cache.caffeine.member.service;

import com.study.cache.caffeine.member.domain.Member;
import com.study.cache.caffeine.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Cacheable(cacheNames = "userCache")
    public Member findById(int id) {
        return memberRepository.findById(id)
                .orElseThrow();
    }
}
