package com.example.demo.repository;

import com.example.demo.model.PricingRule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricingRuleRepository extends JpaRepository<PricingRule, Long> {
    boolean existsByRuleCode(String code);
    List<PricingRule> findByActiveTrue();
}
