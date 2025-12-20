package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.PricingRule;
import com.example.demo.repository.PricingRuleRepository;
import com.example.demo.service.PricingRuleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PricingRuleServiceImpl implements PricingRuleService {

    private final PricingRuleRepository ruleRepo;

    public PricingRuleServiceImpl(PricingRuleRepository ruleRepository) {
        this.ruleRepo = ruleRepository;
    }

    @Override
    public PricingRule createRule(PricingRule rule) {

        if (ruleRepo.existsByRuleCode(rule.getRuleCode())) {
            throw new BadRequestException("Event code already exists");
        }

        if (rule.getPriceMultiplier() == null || rule.getPriceMultiplier() <= 0) {
            throw new BadRequestException("Price multiplier must be > 0");
        }

        return ruleRepo.save(rule);
    }

    @Override
    public PricingRule updateRule(Long id, PricingRule r) {

        PricingRule rule = ruleRepo.findById(id)
                .orElseThrow(() -> new BadRequestException("Rule not found"));

        rule.setDescription(r.getDescription());
        rule.setActive(r.getActive());
        rule.setDaysBeforeEvent(r.getDaysBeforeEvent());
        rule.setMinRemainingSeats(r.getMinRemainingSeats());
        rule.setMaxRemainingSeats(r.getMaxRemainingSeats());
        rule.setPriceMultiplier(r.getPriceMultiplier());

        return ruleRepo.save(rule);
    }

    @Override
    public List<PricingRule> getActiveRules() {
        return ruleRepo.findByActiveTrue();
    }

    @Override
    public Optional<PricingRule> getRuleByCode(String ruleCode) {
        return ruleRepo.findAll()
            .stream()
            .filter(r -> r.getRuleCode().equals(ruleCode))
            .findFirst();
}


    @Override
    public List<PricingRule> getAllRules() {
        return ruleRepo.findAll();
    }
}
