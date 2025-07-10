package com.trasky.gtin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trasky.gtin.dtos.ProductRequest;
import com.trasky.gtin.entity.Product;
import com.trasky.gtin.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create/product")
    public Product createProduct(@RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    @GetMapping("/product/getall")
    public List<Product> getAllProduct(){
        return productService.findAllProduct();
    }
    
    @GetMapping("/product/getbyId/{id}")
    public Optional<Product> getProductById(@PathVariable long id){
        return productService.findProductById(id);
    }
}
