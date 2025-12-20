package com.example.demo.controller;

import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pricing")
public class DynamicPricingController {

    private final DynamicPricingEngineService pricingService;

    public DynamicPricingController(DynamicPricingEngineService pricingService) {
        this.pricingService = pricingService;
    }

    @PostMapping("/{eventId}")
    public DynamicPriceRecord calculatePrice(@PathVariable Long eventId) {
        return pricingService.calculatePrice(eventId);
    }

    @GetMapping("/event/{eventId}")
    public List<DynamicPriceRecord> getPriceHistory(@PathVariable Long eventId) {
        return pricingService.getPriceHistory(eventId);
    }
}
