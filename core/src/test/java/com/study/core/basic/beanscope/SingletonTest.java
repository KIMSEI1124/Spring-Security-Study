package com.study.core.basic.beanscope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class SingletonTest {
    @Test
    void singletonBeanTest() {
        /* Given */
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        SingletonBean bean1 = ac.getBean(SingletonBean.class);
        SingletonBean bean2 = ac.getBean(SingletonBean.class);

        /* When */
        log.info("SingletonBean1 : [{}]", bean1);
        log.info("SingletonBean2 : [{}]", bean2);

        /* Then */
        assertThat(bean1).isSameAs(bean2);
        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean {
        @PostConstruct
        public void init() {
            log.info("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            log.info("SingletonBean.destroy");
        }
    }
}
