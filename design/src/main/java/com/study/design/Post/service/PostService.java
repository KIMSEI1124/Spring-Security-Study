package com.study.design.Post.service;

import com.study.design.Post.domain.Post;
import com.study.design.Post.domain.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {
    private final PostRepository postRepository;

    public Post save(Post post) {
        return postRepository.save(post);
    }
}
