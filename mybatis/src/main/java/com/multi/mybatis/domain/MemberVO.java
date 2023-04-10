package com.multi.mybatis;

import lombok.*;

/*
create table member (
	id varchar(20) primary key,
    pw varchar(20) not null,
    email varchar(50) not null
);
 */

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberVO {
    private String id;
    private String pw;
    private String email;

    @Builder
    public MemberVO(String id, String pw, String email) {
        this.id = id;
        this.pw = pw;
        this.email = email;
    }
}
