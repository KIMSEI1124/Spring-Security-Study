package com.study.design.member.domain;

import com.study.design.Post.domain.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private int id;

    @Column(name = "member_name")
    private String name;

    @OneToMany(mappedBy = "member")
    private List<Post> posts;

    protected Member(String name) {
        this.name = name;
        posts = new ArrayList<>();
    }

    public static Member from(String name) {
        return new Member(name);
    }

    public void removeAllPosts() {
        for (Post post : posts) {
            post.removeRelatedMember(this);
        }
    }
}
