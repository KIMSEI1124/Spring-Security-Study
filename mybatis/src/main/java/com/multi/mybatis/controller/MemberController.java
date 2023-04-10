package com.multi.mybatis;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberRepositoryImpl memberServiceImpl;

    @GetMapping("/getMembers")
    ArrayList<MemberVO> getMembers() {
        return memberServiceImpl.getMembers();
    }

    @PostMapping("/insertMember")
    public int insertMember(MemberVO member) {
        return memberServiceImpl.insertMember(member);
    }

    @GetMapping("/selectMember")
    public MemberVO selectMember(@RequestParam String id) {
        return memberServiceImpl.selectMember(id);
    }

    @GetMapping("/selectMember2")
    public HashMap<String, String> selectMember2(String id) {
        return memberServiceImpl.selectMember2(id);
    }

    @GetMapping("/selectMember3")
    public HashMap<String, String> selectMember3(String id) {
        return memberServiceImpl.selectMember3(id);
    }

    @GetMapping("/selectMemberDto")
    public ResponseEntity<MemberResponse> selectMemberDto(String id) {
        MemberResponse response = memberServiceImpl.selectMemberDto(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/selectMemberDto2")
    public MemberResponse selectMemberDto2(@RequestParam String id) {
        return memberServiceImpl.selectMemberDto(id);
    }

    @PostMapping("/updateMember")
    public int updateMember(MemberVO member) {
        return memberServiceImpl.updateMember(member);
    }

    @PostMapping("/deleteMember")
    public int deleteMember(String id) {
        return memberServiceImpl.deleteMember(id);
    }

    @GetMapping("/countMember")
    public int countMember() {
        return memberServiceImpl.countMember();
    }

}
