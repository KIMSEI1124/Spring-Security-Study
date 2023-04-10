package com.study.controller_advice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(String name) {
        Member saveMember = Member.builder()
                .name(name)
                .build();
        memberRepository.save(saveMember);
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
