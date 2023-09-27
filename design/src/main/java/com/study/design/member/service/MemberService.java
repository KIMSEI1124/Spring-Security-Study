package com.study.design.member.service;

import com.study.design.member.domain.Member;
import com.study.design.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member save(String name) {
        return memberRepository.save(Member.from(name));
    }

    public Member findById(int id) {
        return memberRepository.findById(id)
                .orElseThrow();
    }
}
