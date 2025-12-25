package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rules")
public class PricingRuleController {

    @PostMapping
    public Map<String, Object> createRule(
            @RequestBody Map<String, Object> rule
    ) {
        return Map.of(
                "message", "Pricing rule created successfully",
                "rule", rule
        );
    }

    @GetMapping
    public List<Map<String, Object>> getAllRules() {
        return List.of();
    }

    @GetMapping("/active")
    public List<Map<String, Object>> getActiveRules() {
        return List.of();
    }
}
