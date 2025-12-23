
package com.example.demo.repository;

import com.example.demo.model.PricingRule;
import java.util.*;

public interface PricingRuleRepository {
    boolean existsByRuleCode(String code);
    List<PricingRule> findByActiveTrue();
    List<PricingRule> findAll();
    PricingRule save(PricingRule r);
}
