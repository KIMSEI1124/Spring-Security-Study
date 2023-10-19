package com.study.jpa.post.service;

import com.study.jpa.post.domain.Post;
import com.study.jpa.post.domain.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Post save(String content) {
        Post post = Post.from(content);
        return postRepository.save(post);
    }

    public Post findById(int id) {
        return postRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<Post> findAllByDisableWhereAnnotation() {
        return postRepository.findAllByDisableWhereAnnotation();
    }

    @Transactional
    public void deleteById(int id) {
        Post findPost = findById(id);
        findPost.delete();
    }
}
