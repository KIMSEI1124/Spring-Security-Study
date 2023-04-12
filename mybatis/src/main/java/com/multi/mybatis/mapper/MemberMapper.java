package com.multi.mybatis.mapper;

import com.multi.mybatis.dto.response.MemberResponse;
import com.multi.mybatis.domain.MemberVO;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;

public interface MemberMapper {
    ArrayList<MemberVO> getMembers();

    int insertMember(MemberVO member);

    MemberVO selectMember(@Param("id") String id);

    MemberResponse selectMemberDto(@Param("id") String id);

    int updateMember(MemberVO member);

    int deleteMember(@Param("id") String id); //@Param("id") : 파라메터가 하나일때는 생략 가능

    int countMember();

}
