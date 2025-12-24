package com.example.demo.controller;

import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pricing")
public class DynamicPriceController {

    private final DynamicPricingEngineService pricingEngineService;

    public DynamicPriceController(
            DynamicPricingEngineService pricingEngineService
    ) {
        this.pricingEngineService = pricingEngineService;
    }

    @GetMapping("/event/{eventId}")
    public DynamicPriceRecord computePrice(
            @PathVariable Long eventId
    ) {
        return pricingEngineService.computeDynamicPrice(eventId);
    }

    @GetMapping("/event/{eventId}/history")
    public List<DynamicPriceRecord> getPriceHistory(
            @PathVariable Long eventId
    ) {
        return pricingEngineService.getPriceHistory(eventId);
    }

    @GetMapping
    public List<DynamicPriceRecord> getAllComputedPrices() {
        return pricingEngineService.getAllComputedPrices();
    }
}
