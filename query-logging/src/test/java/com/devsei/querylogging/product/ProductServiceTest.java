package com.devsei.querylogging.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    void threadTest() throws InterruptedException {
        Thread thread1 = new Thread(
                () -> productService.save()
        );
        Thread thread2 = new Thread(
                () -> productService.save()
        );
        thread1.start();
        thread2.start();

        Thread.sleep(4000);
    }
}