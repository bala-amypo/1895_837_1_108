package com.example.demo.service.impl;

import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.repository.SeatInventoryRecordRepository;
import com.example.demo.service.SeatInventoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatInventoryServiceImpl implements SeatInventoryService {

    private final SeatInventoryRecordRepository repo;

    public SeatInventoryServiceImpl(SeatInventoryRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public SeatInventoryRecord create(SeatInventoryRecord record) {
        return repo.save(record);
    }

    @Override
    public List<SeatInventoryRecord> getAll() {
        return repo.findAll();
    }

    @Override
    public List<SeatInventoryRecord> getByEvent(String eventCode) {
        // Convert eventCode (String) to Long before calling repository method
        Long eventId = Long.parseLong(eventCode);
        return repo.findByEventId(eventId);
    }
}
