package com.study.jpa.post.domain;

import com.study.jpa.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Where(clause = "is_delete = false")
@Entity
@Table(catalog = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    private boolean isDelete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Post from(String content) {
        return Post.builder()
                .content(content)
                .build();
    }

    public void delete() {
        isDelete = true;
    }
}
