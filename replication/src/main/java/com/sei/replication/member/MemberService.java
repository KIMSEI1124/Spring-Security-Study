package com.sei.replication.member;

import com.sei.replication.member.dto.MemberSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Member save(MemberSaveRequest request) {
        Member saveMember = Member.from(request);

        return memberRepository.save(saveMember);
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).get();
    }
}
