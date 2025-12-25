package com.example.demo.controller;

import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pricing")
public class DynamicPricingController {

    private final DynamicPricingEngineService service;

    public DynamicPricingController(DynamicPricingEngineService service) {
        this.service = service;
    }

    @PostMapping("/compute/{eventId}")
    public DynamicPriceRecord computePrice(
            @PathVariable Long eventId) {
        return service.computeDynamicPrice(eventId);
    }

    @GetMapping("/history/{eventId}")
    public List<DynamicPriceRecord> priceHistory(
            @PathVariable Long eventId) {
        return service.getPriceHistory(eventId);
    }

    @GetMapping
    public List<DynamicPriceRecord> allComputedPrices() {
        return service.getAllComputedPrices();
    }
}
