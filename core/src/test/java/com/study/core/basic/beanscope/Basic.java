package com.study.core.basic.beanscope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class Basic {

    /* 컴포넌트 스캔 자동 등록 */
    @Scope("prototype")
    @Component
    static class HelloBean {
    }
}
