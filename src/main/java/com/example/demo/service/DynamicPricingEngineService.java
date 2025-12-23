package com.example.demo.service;

import com.example.demo.model.DynamicPriceRecord;
import java.util.List;
import java.util.Optional;

public interface DynamicPricingEngineService {

    // test-used
    DynamicPriceRecord computeDynamicPrice(Long eventId);
    List<DynamicPriceRecord> getPriceHistory(Long eventId);
    List<DynamicPriceRecord> getAllComputedPrices();

    // controller-used
    DynamicPriceRecord save(DynamicPriceRecord record);
    List<DynamicPriceRecord> findAll();
    Optional<DynamicPriceRecord> findById(Long id);
    void deleteById(Long id);
}
