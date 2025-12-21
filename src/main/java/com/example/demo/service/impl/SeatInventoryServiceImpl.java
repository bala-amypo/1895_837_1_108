package com.example.demo.service.impl;

import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.repository.SeatInventoryRepository;
import com.example.demo.service.SeatInventoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatInventoryServiceImpl implements SeatInventoryService {

    private final SeatInventoryRepository repository;

    public SeatInventoryServiceImpl(SeatInventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public SeatInventoryRecord create(SeatInventoryRecord record) {
        return repository.save(record);
    }

    @Override
    public List<SeatInventoryRecord> getAll() {
        return repository.findAll();
    }

    @Override
    public SeatInventoryRecord getByEventId(String eventCode) {
        return repository.findByEventId(Long.parseLong(eventCode))
                .orElseThrow(() -> new RuntimeException("Seat inventory not found"));
    }
}
