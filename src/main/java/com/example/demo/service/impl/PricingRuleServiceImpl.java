package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.PricingRule;
import com.example.demo.repository.PricingRuleRepository;
import com.example.demo.service.PricingRuleService;
import java.util.List;

public class PricingRuleServiceImpl implements PricingRuleService {

    private final PricingRuleRepository repo;

    public PricingRuleServiceImpl(PricingRuleRepository repo) {
        this.repo = repo;
    }

    public PricingRule createRule(PricingRule rule) {
        if (rule.getPriceMultiplier() == null || rule.getPriceMultiplier() <= 0)
            throw new BadRequestException("Price multiplier must be > 0");
        if (repo.existsByRuleCode(rule.getRuleCode()))
            throw new BadRequestException("Rule code exists");
        return repo.save(rule);
    }

    public List<PricingRule> getAllRules() {
        return repo.findAll();
    }

    public List<PricingRule> getActiveRules() {
        return repo.findByActiveTrue();
    }
}
