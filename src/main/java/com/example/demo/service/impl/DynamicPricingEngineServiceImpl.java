package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
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

    @Override
    public DynamicPriceRecord save(DynamicPriceRecord record) {
        if (record.getEvent() != null && Boolean.FALSE.equals(record.getEvent().getActive())) {
            throw new BadRequestException("Event is not active");
        }
        return repository.save(record);
    }

    @Override
    public List<DynamicPriceRecord> findAll() {
        return repository.findAll();
    }

    @Override
    public DynamicPriceRecord findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
