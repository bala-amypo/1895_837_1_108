package com.example.demo.repository;

import com.example.demo.model.PricingRule;
import java.util.List;

public interface PricingRuleRepository {

    boolean existsByRuleCode(String ruleCode);

    List<PricingRule> findByActiveTrue();

    List<PricingRule> findAll();

    PricingRule save(PricingRule rule);
}
