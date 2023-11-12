package com.core.member;

import com.core.member.dto.req.MemberSaveReq;
import com.core.team.TeamFindService;
import com.data.member.Member;
import com.data.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final TeamFindService teamFindService;

    @Transactional
    public Member member(MemberSaveReq req) {
        return memberRepository.save(req.toEntity(
                teamFindService.findById(req.teamId())));
    }
}
