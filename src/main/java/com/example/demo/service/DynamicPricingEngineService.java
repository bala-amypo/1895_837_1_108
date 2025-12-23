package com.example.demo.service;

import com.example.demo.model.DynamicPriceRecord;

import java.util.List;

public interface DynamicPricingEngineService {

    // ===== Used by TESTS =====
    DynamicPriceRecord computeDynamicPrice(Long eventId);
    List<DynamicPriceRecord> getPriceHistory(Long eventId);
    List<DynamicPriceRecord> getAllComputedPrices();

    // ===== Used by CONTROLLERS =====
    DynamicPriceRecord save(DynamicPriceRecord record);
    List<DynamicPriceRecord> findAll();
    DynamicPriceRecord findById(Long id);
    void deleteById(Long id);
}
