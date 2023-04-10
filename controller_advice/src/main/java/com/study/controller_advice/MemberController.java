package com.study.controller_advice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/member")
@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> add(@Param("name") String name) {
        memberService.save(name);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Member> get(@Param("id") Long id) {
        Member findMember = memberService.findById(id);
        return ResponseEntity.ok(findMember);
    }

    @GetMapping("/not-found")
    public ResponseEntity<Void> notfound() {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/bad-request")
    public ResponseEntity<Void> badRequest() {
        return ResponseEntity.badRequest().build();
    }
}
