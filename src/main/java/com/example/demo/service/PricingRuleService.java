package com.example.demo.service;

import com.example.demo.model.PricingRule;
import java.util.List;
import java.util.Optional;

public interface PricingRuleService {

    // test-used
    PricingRule createRule(PricingRule rule);
    List<PricingRule> getActiveRules();
    List<PricingRule> getAllRules();

    // controller-used
    PricingRule save(PricingRule rule);
    List<PricingRule> findAll();
    Optional<PricingRule> findById(Long id);
    void deleteById(Long id);
}
