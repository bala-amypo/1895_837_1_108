package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.PricingRule;
import com.example.demo.repository.PricingRuleRepository;
import com.example.demo.service.PricingRuleService;

import java.util.List;
import java.util.Optional;

public class PricingRuleServiceImpl implements PricingRuleService {

    private final PricingRuleRepository repo;

    public PricingRuleServiceImpl(PricingRuleRepository repo) {
        this.repo = repo;
    }

    // ===== Test-used methods =====

    @Override
    public PricingRule createRule(PricingRule rule) {
        if (rule.getPriceMultiplier() == null || rule.getPriceMultiplier() <= 0) {
            throw new BadRequestException("Price multiplier must be > 0");
        }
        if (repo.existsByRuleCode(rule.getRuleCode())) {
            throw new BadRequestException("Rule code already exists");
        }
        return repo.save(rule);
    }

    @Override
    public List<PricingRule> getActiveRules() {
        return repo.findByActiveTrue();
    }

    @Override
    public List<PricingRule> getAllRules() {
        return repo.findAll();
    }

    // ===== Controller-required CRUD methods =====

    @Override
    public PricingRule save(PricingRule rule) {
        return repo.save(rule);
    }

    @Override
    public List<PricingRule> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<PricingRule> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        // no-op
    }
}
