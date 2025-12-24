package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/pricing")
public class DynamicPriceController {

    @GetMapping("/event/{eventId}")
    public String getDynamicPrice(@PathVariable Long eventId) {
        return "Dynamic price calculated for eventId = " + eventId;
    }

    @PostMapping
    public String calculatePrice(@RequestBody Map<String, Object> pricing) {

        Long eventId = Long.valueOf(pricing.get("eventId").toString());
        Double basePrice = Double.valueOf(pricing.get("basePrice").toString());
        Integer remainingSeats = Integer.valueOf(pricing.get("remainingSeats").toString());
        Integer daysBeforeEvent = Integer.valueOf(pricing.get("daysBeforeEvent").toString());
        Double multiplier = Double.valueOf(pricing.get("priceMultiplier").toString());

        Double finalPrice = basePrice * multiplier;

        return "Dynamic price calculated: " +
                "eventId=" + eventId +
                ", finalPrice=" + finalPrice;
    }
}
