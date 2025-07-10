package com.trasky.gtin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trasky.gtin.dtos.BatchRequest;
import com.trasky.gtin.entity.Batch;
import com.trasky.gtin.service.BatchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BatchController {

    private final BatchService batchService;

    @PostMapping("/create/batch")
    public Batch createBatch(@RequestBody BatchRequest request) {
        return batchService.createBatch(request);
    }

    @GetMapping("/batch/available")
    public List<Batch> getAllBatch() {
        return batchService.findAllbBatch();
    }

    @GetMapping("/batch/findpostive")
    public List<Batch> findPostiBatchs() {
        return batchService.findAllbBatch();
    }

    @GetMapping("/batch/findnegative")
    public List<Batch> findZeroAndNegBatchs() {
        return batchService.findAllbBatch();
    }
     @GetMapping("/batch/findById/{id}")
    public Optional<Batch> findBathFromId(@PathVariable long batchId) {
        return batchService.findByBatchId(batchId);
    }
}
