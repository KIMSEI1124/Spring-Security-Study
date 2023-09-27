package com.study.design.patterns.facade;

import com.study.design.Post.domain.Post;
import com.study.design.Post.service.PostService;
import com.study.design.member.domain.Member;
import com.study.design.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostFacadeService {
    private final MemberService memberService;
    private final PostService postService;

    @Transactional
    public Post save(int memberId, String title, String content) {
        Member member = memberService.findById(memberId);
        return postService.save(Post.of(title, content, member));
    }
}
