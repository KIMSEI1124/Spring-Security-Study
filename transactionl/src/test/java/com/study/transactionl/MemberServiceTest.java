package com.study.transactionl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    private long beforeTime;

    @BeforeEach
    void startTimer() {
        beforeTime = System.currentTimeMillis();
    }

    @AfterEach
    void endTimer() {
        long afterTime = System.currentTimeMillis();
        log.info("시간차이(ms) : {}", afterTime - beforeTime);
    }

    @DisplayName("readOnly로 조회한다")
    @Test
    void readOnly로_조회한다() {
        for (long i = 0; i < 5_000_000; i++) {
            memberService.findByIdWithReadOnly(i);
        }
    }

    @DisplayName("기본으로 조회한다")
    @Test
    void 기본으로_조회한다() {
        for (long i = 0; i < 5_000_000; i++) {
            memberService.findById(i);
        }
    }

    @Test
    void saveMember() {
        for (int i = 0; i < 5_000_000; i++) {
            memberService.save(new Member("이름은 " + i));
        }
    }

    @Test
    void logCount() {
        long count = memberService.count();
        log.info("Member Count : [{}]", count);
    }
}