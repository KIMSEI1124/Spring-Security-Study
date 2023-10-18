package com.study.core.basic.beanscope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class PrototypeTest {
    @Test
    public void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(PrototypeBean.class);

        log.info("find PrototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        log.info("find PrototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        log.info("PrototypeBean1 : [{}]", prototypeBean1);
        log.info("PrototypeBean2 : [{}]", prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.인스턴스 이니셜라이저");
        }
    }
}
