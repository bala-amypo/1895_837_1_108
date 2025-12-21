package com.example.demo.controller;

import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.service.DynamicPricingEngineService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pricing")
public class DynamicPricingController {

    private final DynamicPricingEngineService service;

    public DynamicPricingController(DynamicPricingEngineService service) {
        this.service = service;
    }

    @PostMapping("/compute/{eventId}")
    public DynamicPriceRecord compute(@PathVariable Long eventId) {
        return service.computePrice(eventId);
    }

    @GetMapping
    public List<DynamicPriceRecord> getAll() {
        return service.getAllComputedPrices();
    }
}
