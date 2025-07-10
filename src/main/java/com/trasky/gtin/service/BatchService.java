package com.trasky.gtin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.trasky.gtin.dtos.BatchRequest;
import com.trasky.gtin.entity.Batch;
import com.trasky.gtin.entity.Product;
import com.trasky.gtin.repositories.BatchRepository;
import com.trasky.gtin.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BatchService {

    private final ProductRepository productRepo;
    private final BatchRepository batchRepo;

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

    public List<Batch> findAllbBatch(){
        return batchRepo.findAll();
    }

    public List<Batch> findpostiveBatch(){
        return batchRepo.findPositiveBatches();
    }

    public List<Batch> findZeroOrNegativeBatches(){
        return batchRepo.findZeroOrNegativeBatches();
    }

    public Optional<Batch> findByBatchId(long batchId) {
        return batchRepo.findById(batchId);
    }

}
