package com.multi.mybatis.controller;

import com.multi.mybatis.domain.MemberVO;
import com.multi.mybatis.dto.response.MemberResponse;
import com.multi.mybatis.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/findAll")
    List<MemberVO> getMembers() {
        return memberService.findAll();
    }

    @PostMapping("/save")
    public int insertMember(MemberVO member) {
        return memberService.save(member);
    }

    @GetMapping("/findByVO")
    public MemberVO selectMember(@RequestParam String id) {
        return memberService.findByVO(id);
    }

    @GetMapping("/findByDto")
    public MemberResponse selectMemberDto2(@RequestParam String id) {
        return memberService.findByResponse(id);
    }

    @PostMapping("/update")
    public int updateMember(MemberVO member) {
        return memberService.update(member);
    }

    @DeleteMapping("/delete")
    public int deleteMember(String id) {
        return memberService.delete(id);
    }

    @GetMapping("/count")
    public int countMember() {
        return memberService.count();
    }
}
