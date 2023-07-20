package com.study.transactionl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public void findByIdWithReadOnly(Long id) {
        memberRepository.findById(id);
    }

    @Transactional
    public void findById(Long id) {
        memberRepository.findById(id);
    }

    @Transactional
    public void save(Member member) {
        memberRepository.save(member);
    }

    public long count() {
        return memberRepository.count();
    }
}
