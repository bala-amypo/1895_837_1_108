package com.example.demo.service.impl;

import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.repository.DynamicPriceRecordRepository;
import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DynamicPricingEngineServiceImpl implements DynamicPricingEngineService {

    private final DynamicPriceRecordRepository repo;

    public DynamicPricingEngineServiceImpl(DynamicPriceRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public DynamicPriceRecord computeDynamicPrice(Long eventId) {
        DynamicPriceRecord record = new DynamicPriceRecord();
        record.setEventId(eventId);
        record.setPrice(100.0); // example default price
        record.setCalculatedAt(LocalDateTime.now());
        return repo.save(record);
    }

    @Override
    public List<DynamicPriceRecord> getPriceHistory(Long eventId) {
        return repo.findByEventId(eventId);
    }

    @Override
    public Optional<DynamicPriceRecord> getLatestPrice(Long eventId) {
        return repo.findTopByEventIdOrderByCalculatedAtDesc(eventId);
    }

    @Override
    public List<DynamicPriceRecord> getAllComputedPrices() {
        return repo.findAll();
    }
}
