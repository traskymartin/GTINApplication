package com.trasky.gtin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trasky.gtin.dtos.GtinRequest;
import com.trasky.gtin.entity.Batch;
import com.trasky.gtin.entity.Gtin;
import com.trasky.gtin.service.GTINService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GTINController {

    private final GTINService gtinService;

    @PostMapping("/create/gtin")
    public Gtin createGtin(@RequestBody GtinRequest request) {
        return gtinService.createGtin(request);
    }

    @GetMapping("/gtins/{gtin}")
    public List<Gtin> getGtin(@PathVariable String gtin) {
        return gtinService.getGtinByCode(gtin);
    }

    @GetMapping("/gtins/available")
    public Map<String, List<Batch>> getFilteredGtins() {
        return gtinService.getFilteredGtins();
    }
}
