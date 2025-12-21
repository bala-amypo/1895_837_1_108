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

    @PostMapping("/{eventId}")
    public DynamicPriceRecord compute(@PathVariable Long eventId) {
        return service.compute(eventId);
    }

    @GetMapping
    public List<DynamicPriceRecord> getAll() {
        return service.getAllComputedPrices();
    }

    @GetMapping("/{eventId}")
    public List<DynamicPriceRecord> history(@PathVariable Long eventId) {
        return service.getPriceHistory(eventId);
    }
}
