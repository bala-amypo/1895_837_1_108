package com.example.demo.controller;

import com.example.demo.model.PricingRule;
import com.example.demo.service.PricingRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class PricingRuleController {

    private final PricingRuleService service;

    public PricingRuleController(PricingRuleService service) {
        this.service = service;
    }

    @PostMapping
    public PricingRule createRule(@RequestBody PricingRule rule) {
        return service.createRule(rule);
    }

    @GetMapping
    public List<PricingRule> getAllRules() {
        return service.getAllRules();
    }

    @GetMapping("/active")
    public List<PricingRule> getActiveRules() {
        return service.getActiveRules();
    }
}
