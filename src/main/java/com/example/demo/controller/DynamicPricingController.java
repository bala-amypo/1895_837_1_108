package com.example.demo.controller;

import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pricing")
public class DynamicPricingController {

    private final DynamicPricingEngineService dynamicPricingEngineService;

    public DynamicPricingController(DynamicPricingEngineService dynamicPricingEngineService) {
        this.dynamicPricingEngineService = dynamicPricingEngineService;
    }

    @PostMapping("/compute/{eventId}")
    public DynamicPriceRecord computePrice(@PathVariable Long eventId) {
        return dynamicPricingEngineService.computeDynamicPrice(eventId);
    }

    @GetMapping("/history/{eventId}")
    public List<DynamicPriceRecord> getPriceHistory(@PathVariable Long eventId) {
        return dynamicPricingEngineService.getPriceHistory(eventId);
    }

    @GetMapping("/all")
    public List<DynamicPriceRecord> getAllPrices() {
        return dynamicPricingEngineService.getAllComputedPrices();
    }
}
