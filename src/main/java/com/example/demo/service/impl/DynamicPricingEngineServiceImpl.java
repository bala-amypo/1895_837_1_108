package com.example.demo.service.impl;

import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.repository.DynamicPriceRecordRepository;
import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DynamicPricingEngineServiceImpl implements DynamicPricingEngineService {

    private final DynamicPriceRecordRepository repository;

    public DynamicPricingEngineServiceImpl(DynamicPriceRecordRepository repository) {
        this.repository = repository;
    }

    public DynamicPriceRecord save(DynamicPriceRecord record) {
        return repository.save(record);
    }

    public List<DynamicPriceRecord> findAll() {
        return repository.findAll();
    }

    public DynamicPriceRecord findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
