package com.study.jpa.post.service;

import com.study.jpa.post.domain.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostServiceTest {
    @Autowired
    private PostService postService;
    private Post postOne;
    private Post postTwo;
    private Post postThree;

    @BeforeEach
    void setUp() {
        postOne = postService.save("포스트1");
        postTwo = postService.save("포스트2");
        postThree = postService.save("포스트3");
    }

    @DisplayName("@Where 동작에 성공한다.")
    @Test
    void whereAnnotationTest() {
        /* Given */
        int id = postTwo.getId();
        postService.deleteById(id);

        /* When */
        List<Post> postList = postService.findAll();

        /* Then */
        assertThat(postList).hasSize(2);
    }

    @DisplayName("@Where 비활성화에 성공한다.")
    @Test
    void whereAnnotationDisableTest() {
        /* Given */
        int id = postTwo.getId();
        postService.deleteById(id);

        /* When */
        List<Post> postList = postService.findAllByDisableWhereAnnotation();

        /* Then */
        assertThat(postList).hasSize(3);
    }
}