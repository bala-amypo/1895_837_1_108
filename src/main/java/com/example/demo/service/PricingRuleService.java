package com.example.demo.service;

import com.example.demo.model.PricingRule;
import java.util.List;

public interface PricingRuleService {
    PricingRule save(PricingRule rule);
    List<PricingRule> findAll();
    PricingRule findById(Long id);
    void deleteById(Long id);
}
