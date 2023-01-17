package com.study.datajpa.repository;

public class UsernameOnlyDto {
    private final long id;
    private final String username;

    public UsernameOnlyDto(String username, long id) {
        this.username = username;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
