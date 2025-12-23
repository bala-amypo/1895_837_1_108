package com.example.demo.service;

import com.example.demo.model.PricingRule;

import java.util.List;

public interface PricingRuleService {

    // ===== Used by TESTS =====
    PricingRule createRule(PricingRule rule);
    List<PricingRule> getActiveRules();
    List<PricingRule> getAllRules();

    // ===== Used by CONTROLLERS =====
    PricingRule save(PricingRule rule);
    List<PricingRule> findAll();
    PricingRule findById(Long id);
    void deleteById(Long id);
}
