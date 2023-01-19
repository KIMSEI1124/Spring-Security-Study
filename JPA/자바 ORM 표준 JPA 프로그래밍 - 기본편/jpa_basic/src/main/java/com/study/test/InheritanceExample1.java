package com.study.test;

public class InheritanceExample1 {
    public static void main(String[] args) {
        CheckingAccount obj = new CheckingAccount();
        obj.id = "111-22-33333333";
        obj.name = "홍길동";
        obj.cardNumber = "5555-6666-7777-8888";
        obj.deposit(100000);
        try {
            int paidAmount = obj.pay("5555-6666-7777-8888", 47000);
            System.out.println("지불액 = " + paidAmount);
            System.out.println("잔액 = " + obj.balance);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
