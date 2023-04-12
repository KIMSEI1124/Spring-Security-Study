package com.multi.mybatis.service;

import com.multi.mybatis.domain.MemberVO;
import com.multi.mybatis.dto.response.MemberResponse;
import com.multi.mybatis.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public List<MemberVO> findAll() {
        return memberRepository.getMembers();
    }

    public int save(MemberVO vo) {
        return memberRepository.insertMember(vo);
    }

    public MemberVO findByVO(String id) {
        return memberRepository.selectMember(id);
    }

    public MemberResponse findByResponse(String id) {
        return memberRepository.selectMemberDto(id);
    }

    public int update(MemberVO vo) {
        return memberRepository.updateMember(vo);
    }

    public int delete(String id) {
        return memberRepository.deleteMember(id);
    }

    public int count() {
        return memberRepository.countMember();
    }
}
