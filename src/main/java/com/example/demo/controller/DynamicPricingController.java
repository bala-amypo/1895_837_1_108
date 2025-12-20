package com.example.demo.controller;

import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pricing")
public class DynamicPricingController {

    private final DynamicPricingEngineService pricingService;

    public DynamicPricingController(DynamicPricingEngineService pricingService) {
        this.pricingService = pricingService;
    }

    @PostMapping("/compute/{eventId}")
    public DynamicPriceRecord computePrice(@PathVariable Long eventId) {
        return pricingService.computeDynamicPrice(eventId);
    }

    @GetMapping("/history/{eventId}")
    public List<DynamicPriceRecord> getHistory(@PathVariable Long eventId) {
        return pricingService.getPriceHistory(eventId);
    }

    @GetMapping("/latest/{eventId}")
    public Optional<DynamicPriceRecord> getLatest(@PathVariable Long eventId) {
        return pricingService.getLatestPrice(eventId);
    }

    @GetMapping("/all")
    public List<DynamicPriceRecord> getAll() {
        return pricingService.getAllComputedPrices();
    }
}
