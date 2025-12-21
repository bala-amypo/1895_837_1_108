package com.example.demo.service;

import com.example.demo.model.DynamicPriceRecord;
import java.util.List;

public interface DynamicPricingEngineService {

    DynamicPriceRecord compute(Long eventId);

    List<DynamicPriceRecord> getAllComputedPrices();

    List<DynamicPriceRecord> getPriceHistory(Long eventId);
}
