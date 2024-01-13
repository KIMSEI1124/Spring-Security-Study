package com.study.module.member;

import com.study.module.dto.req.MemberSaveReq;
import com.study.module.team.TeamFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberDomainService service;
    private final TeamFindService teamFindService;

    @Transactional
    public Member member(MemberSaveReq req) {
        return service.save(req.toEntity(
                teamFindService.findById(req.teamId())));
    }
}
