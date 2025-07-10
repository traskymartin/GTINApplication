package com.trasky.gtin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.trasky.gtin.dtos.GtinRequest;
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
            List<Batch> positive = batchRepo.findPositiveBatchesByProductId(productId);

            List<Batch> latestZeroOrNegative = batchRepo
                .findTop1ByProductProductIdAndAvailableQuantityLessThanEqualOrderByInwardedOnDesc(productId, 0);

            List<Batch> combined = new ArrayList<>(positive);
            combined.addAll(latestZeroOrNegative);

            result.put(gtin.getGtin(), combined);
        }
        return result;
    }
}
