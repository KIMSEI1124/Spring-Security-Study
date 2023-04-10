package com.study.response_entity;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/add")
    public ResponseEntity<Void> add(@Param("name") String name) {
        memberService.save(name);
        return ResponseEntity.ok().build();
    }
}
