package com.devsei.orderservice.ui;

import com.devsei.orderservice.application.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/order-service")
@RestController
public class OrderController {
    private final OrderService orderService;
    private final Environment environment;

    @GetMapping("/health_check")
    public String status() {
        return String.format("%s",
                environment.getProperty("local.server.port"));
    }
}
