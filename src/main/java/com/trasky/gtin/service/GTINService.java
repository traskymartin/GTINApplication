package com.trasky.gtin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.trasky.gtin.dtos.BatchRequest;
import com.trasky.gtin.dtos.GtinRequest;
import com.trasky.gtin.dtos.ProductRequest;
import com.trasky.gtin.entity.Batch;
import com.trasky.gtin.entity.Gtin;
import com.trasky.gtin.entity.Product;
import com.trasky.gtin.repositories.BatchRepository;
import com.trasky.gtin.repositories.GtinRepository;
import com.trasky.gtin.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GTINService {

    private final ProductRepository productRepo;
    private final BatchRepository batchRepo;
    private final GtinRepository gtinRepo;

    public Product createProduct(ProductRequest request) {
        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setCreatedOn(request.getCreatedOn());
        return productRepo.save(product);
    }

    public Batch createBatch(BatchRequest request) {
        Product product = productRepo.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Batch batch = new Batch();
        batch.setProduct(product);
        batch.setMrp(request.getMrp());
        batch.setSp(request.getSp());
        batch.setPurchasePrice(request.getPurchasePrice());
        batch.setAvailableQuantity(request.getAvailableQuantity());
        batch.setInwardedOn(request.getInwardedOn());
        return batchRepo.save(batch);
    }

    public Gtin createGtin(GtinRequest request) {
        Product product = productRepo.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Gtin gtin = new Gtin();
        gtin.setGtin(request.getGtin());
        gtin.setProduct(product);
        return gtinRepo.save(gtin);
    }

    public List<Gtin> getGtinByCode(String gtinCode) {
        return gtinRepo.findByGtin(gtinCode);
    }

    public Map<String, List<Batch>> getFilteredGtins() {
        List<Gtin> allGtins = gtinRepo.findAll();
        Map<String, List<Batch>> result = new HashMap<>();

        for (Gtin gtin : allGtins) {
            Long productId = gtin.getProduct().getProductId();
            List<Batch> positive = batchRepo.findAll().stream()
                .filter(b -> b.getProduct().getProductId().equals(productId) && b.getAvailableQuantity() > 0)
                .collect(Collectors.toList());

            List<Batch> latestZeroOrNegative = batchRepo
                .findTop1ByProductProductIdAndAvailableQuantityLessThanEqualOrderByInwardedOnDesc(productId, 0);

            List<Batch> combined = new ArrayList<>(positive);
            combined.addAll(latestZeroOrNegative);

            result.put(gtin.getGtin(), combined);
        }
        return result;
    }
}
