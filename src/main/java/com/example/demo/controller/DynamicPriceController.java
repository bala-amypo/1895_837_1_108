package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/pricing")
public class DynamicPriceController {

    @GetMapping("/event/{eventId}")
    public String getPrice(@PathVariable Long eventId) {
        return "Dynamic price calculated for eventId = " + eventId;
    }

    // ✅ POST
    @PostMapping
    public String addPricingRule(@RequestBody Map<String, Object> pricingData) {
        return "Pricing rule added: " + pricingData;
    }
}
