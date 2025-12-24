package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pricing")
public class DynamicPriceController {

    /**
     * Swagger/demo endpoint
     * Does NOT inject service (service is not a Spring bean)
     */
    @GetMapping("/event/{eventId}")
    public Map<String, Object> computeDynamicPrice(
            @PathVariable Long eventId
    ) {
        return Map.of(
                "eventId", eventId,
                "computedPrice", 0.0,
                "message", "Dynamic price computation handled in service layer"
        );
    }

    @GetMapping("/event/{eventId}/history")
    public List<Map<String, Object>> getPriceHistory(
            @PathVariable Long eventId
    ) {
        return List.of(
                Map.of(
                        "eventId", eventId,
                        "price", 0.0
                )
        );
    }

    @GetMapping
    public List<Map<String, Object>> getAllComputedPrices() {
        return List.of();
    }
}
