package com.study.test;

public class Account {
    String id;      // 계좌 번호
    String name;    // 예금주 이름
    int balance;    // 잔액

    void deposit(int amount) {
        this.balance += amount;
    }

    int withdraw(int amount) throws Exception {
        if (this.balance < amount) {
            throw new Exception("잔액이 부족합니다.");
        }
        this.balance -= amount;
        return amount;
    }
}
