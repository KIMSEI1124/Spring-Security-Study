package com.study.design.Post.domain;

import com.study.design.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int id;

    @Column(name = "post_title")
    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Post(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        addRelateMember(member);
    }

    public static Post of(String title, String content, Member member) {
        return new Post(title, content, member);
    }

    private void addRelateMember(Member member) {
        this.member = member;
        member.getPosts().add(this);
    }

    public boolean removeRelatedMember(Member member) {
        this.member = null;
        return member.getPosts().remove(this);
    }
}