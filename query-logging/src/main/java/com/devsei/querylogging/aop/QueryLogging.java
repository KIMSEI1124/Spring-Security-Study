package com.devsei.querylogging.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class QueryLogging {
    private final ThreadLocal<StopWatch> threadLocalStopWatch = new ThreadLocal<>();

    @Pointcut("@annotation(com.devsei.querylogging.aop.QueryTimer)")
    private void queryLoggingPointcut() {
    }


    @Before(value = "queryLoggingPointcut()")
    public void queryTimerStart(JoinPoint joinPoint) {
        log.info("Query Timer Start");
        timerStart();
    }

    @AfterReturning(value = "queryLoggingPointcut()")
    public void queryTimer(JoinPoint joinPoint) {
        timerStop();
        log.info("Query Timer End : [{}]", getTotalTimeMillis());
    }

    private void timerStart() {
        threadLocalStopWatch.set(new StopWatch());
        threadLocalStopWatch.get().start();
    }

    private void timerStop() {
        threadLocalStopWatch.get().stop();
    }

    private long getTotalTimeMillis() {
        long totalTimeMillis = threadLocalStopWatch.get().getTotalTimeMillis();
        threadLocalStopWatch.remove();
        return totalTimeMillis;
    }
}
