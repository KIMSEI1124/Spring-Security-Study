package com.devsei.querylogging.product;

import com.devsei.querylogging.aop.QueryTimer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @QueryTimer
    public void save() {
        System.out.println("Hello!");
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
