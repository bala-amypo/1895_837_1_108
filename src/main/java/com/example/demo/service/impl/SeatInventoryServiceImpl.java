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
    public SeatInventoryRecord save(SeatInventoryRecord record) {
        return repository.save(record);
    }

    @Override
    public List<SeatInventoryRecord> findAll() {
        return repository.findAll();
    }

    @Override
    public SeatInventoryRecord findByEventId(Long eventId) {
        return repository.findByEventId(eventId)
                .orElse(null);
    }
}
