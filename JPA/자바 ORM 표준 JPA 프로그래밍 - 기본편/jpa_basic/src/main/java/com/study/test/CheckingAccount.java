package com.study.test;

public class CheckingAccount extends Account {
    String cardNumber;  // 카드 번호

    int pay(String cardNumber, int amount) throws Exception {
        if (!cardNumber.equals(this.cardNumber) || this.balance < amount) {
            throw new Exception("지불이 불가능합니다.");
        }
        return withdraw(amount);
    }
}
