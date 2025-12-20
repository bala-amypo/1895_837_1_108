package com.example.demo.controller;

import com.example.demo.model.PricingRule;
import com.example.demo.service.PricingRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rules")
public class PricingRuleController {

    private final PricingRuleService ruleService;

    public PricingRuleController(PricingRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public PricingRule add(@RequestBody PricingRule rule) {
        return ruleService.createRule(rule);
    }

    @PutMapping("/{id}")
    public PricingRule update(@PathVariable Long id, @RequestBody PricingRule rule) {
        return ruleService.updateRule(id, rule);
    }

    @GetMapping
    public List<PricingRule> list() {
        return ruleService.getAllRules();
    }

    @GetMapping("/active")
    public List<PricingRule> activeRules() {
        return ruleService.getActiveRules();
    }

    @GetMapping("/{code}")
    public Optional<PricingRule> getRule(@PathVariable String code) {
        return ruleService.getRuleByCode(code);
    }
}
