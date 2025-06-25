package com.example.orderservice.controller;

import com.example.common.dto.OrderCreatedEvent;
import com.example.common.dto.ProductDto;
import com.example.orderservice.client.ProductClient;
import com.example.orderservice.dto.CreateOrderRequest;
import com.example.orderservice.kafka.OrderKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final ProductClient productClient;
    private final OrderKafkaProducer orderKafkaProducer;

    @GetMapping("/products")
    public List<ProductDto> getProductsFromProductService() {
        return productClient.getProducts();
    }

    @PostMapping
    public String createOrder(@RequestBody CreateOrderRequest request) {
        String orderId = UUID.randomUUID().toString();

        OrderCreatedEvent event = new OrderCreatedEvent(
                orderId,
                request.getProductId(),
                request.getQuantity()
        );
        orderKafkaProducer.send(event);

        return "✅ 주문 완료 (orderId = " + orderId + ")";
    }
}
