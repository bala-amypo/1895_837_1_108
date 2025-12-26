package com.example.demo.controller;

import com.example.demo.model.PricingRule;
import com.example.demo.service.PricingRuleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pricing-rules")
public class PricingRuleController {

    private final PricingRuleService service;

    public PricingRuleController(PricingRuleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PricingRule> create(@RequestBody PricingRule rule) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createRule(rule));
    }

    @GetMapping
    public ResponseEntity<List<PricingRule>> getAll() {
        return ResponseEntity.ok(service.getAllRules());
    }

    @GetMapping("/active")
    public ResponseEntity<List<PricingRule>> getActive() {
        return ResponseEntity.ok(service.getActiveRules());
    }
}
