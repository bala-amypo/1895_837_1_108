package com.example.demo.controller;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dynamic-pricing")
public class DynamicPricingController {

    private final DynamicPricingEngineService pricingService;

    public DynamicPricingController(DynamicPricingEngineService pricingService) {
        this.pricingService = pricingService;
    }

    @GetMapping("/calculate/{eventId}")
    public DynamicPriceRecord calculatePrice(@PathVariable Long eventId) {
        if (eventId == null || eventId <= 0) {
            throw new BadRequestException("Event ID must be a positive number");
        }

        return pricingService.computeDynamicPrice(eventId);
    }

    @GetMapping("/history/{eventId}")
    public List<DynamicPriceRecord> getPriceHistory(@PathVariable Long eventId) {
        if (eventId == null || eventId <= 0) {
            throw new BadRequestException("Event ID must be a positive number");
        }

        return pricingService.getPriceHistory(eventId);
    }
}
