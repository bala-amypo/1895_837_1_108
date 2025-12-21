package com.example.demo.service;

import com.example.demo.model.DynamicPriceRecord;
import java.util.List;

public interface DynamicPricingEngineService {
    DynamicPriceRecord save(DynamicPriceRecord record);
    List<DynamicPriceRecord> findAll();
    DynamicPriceRecord findById(Long id);
    void deleteById(Long id);
}
