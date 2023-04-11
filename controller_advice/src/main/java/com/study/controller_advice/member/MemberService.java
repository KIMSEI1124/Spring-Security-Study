package com.study.controller_advice.member;

import com.study.controller_advice.member.exception.MemberErrorCode;
import com.study.controller_advice.member.exception.MemberException;
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
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_EXIST));
    }
}
