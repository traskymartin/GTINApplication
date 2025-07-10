package com.trasky.gtin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.trasky.gtin.dtos.ProductRequest;
import com.trasky.gtin.entity.Product;
import com.trasky.gtin.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(ProductRequest request) {
        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setCreatedOn(request.getCreatedOn());
        return productRepository.save(product);
    }

    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    public Optional<Product> findProductById(Long id){
        return productRepository.findById(id);
    }
}
