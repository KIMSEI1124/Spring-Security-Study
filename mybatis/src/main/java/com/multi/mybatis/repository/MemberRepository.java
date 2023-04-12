package com.multi.mybatis.repository;

import com.multi.mybatis.domain.MemberVO;
import com.multi.mybatis.dto.response.MemberResponse;
import com.multi.mybatis.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MemberRepository {
    private final MemberMapper memberMapper;

    public List<MemberVO> getMembers() {
        return memberMapper.getMembers();
    }

    public int insertMember(MemberVO member) {
        return memberMapper.insertMember(member);
    }

    public MemberVO selectMember(String id) {
        return memberMapper.selectMember(id);
    }

    public MemberResponse selectMemberDto(String id) {
        return memberMapper.selectMemberDto(id);
    }

    public int updateMember(MemberVO member) {
        return memberMapper.updateMember(member);
    }

    public int deleteMember(String id) {
        return memberMapper.deleteMember(id);
    }

    public int countMember() {
        return memberMapper.countMember();
    }
}
