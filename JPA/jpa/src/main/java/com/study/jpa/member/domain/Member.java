package com.study.jpa.member.domain;

import com.study.jpa.post.domain.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(catalog = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Post> postList = new ArrayList<>();

    public static Member from(String name) {
        return Member.builder()
                .name(name)
                .build();
    }
}

