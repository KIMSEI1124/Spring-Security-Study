package com.study.jpa.member.service;

import com.study.jpa.member.domain.Member;
import com.study.jpa.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public Member save(String name) {
        return memberRepository.save(Member.from(name));
    }
}
