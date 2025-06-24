package com.example.orderservice.controller;

import com.example.common.dto.ProductDto;
import com.example.orderservice.client.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final ProductClient productClient;

    @GetMapping("/products")
    public List<ProductDto> getProductsFromProductService() {
        return productClient.getProducts();
    }
}
