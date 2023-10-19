package com.study.jpa.post.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(value = "SELECT p.id, p.content, p.is_delete FROM Post p ",nativeQuery = true)
    List<Post> findAllByDisableWhereAnnotation();
}
