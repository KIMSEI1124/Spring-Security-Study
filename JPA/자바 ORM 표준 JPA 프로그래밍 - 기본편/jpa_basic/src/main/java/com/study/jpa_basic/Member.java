package com.study.jpa_basic;

import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
public class Member {
    @Id
    private Long Id;
    private String name;
}
