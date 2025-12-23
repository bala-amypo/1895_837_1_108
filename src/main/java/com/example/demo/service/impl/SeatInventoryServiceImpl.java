package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.repository.EventRecordRepository;
import com.example.demo.repository.SeatInventoryRecordRepository;
import com.example.demo.service.SeatInventoryService;

import java.util.List;

public class SeatInventoryServiceImpl implements SeatInventoryService {

    private final SeatInventoryRecordRepository repo;
    private final EventRecordRepository eventRepo;

    public SeatInventoryServiceImpl(
            SeatInventoryRecordRepository repo,
            EventRecordRepository eventRepo) {
        this.repo = repo;
        this.eventRepo = eventRepo;
    }

    // ===== TEST METHODS =====

    @Override
    public SeatInventoryRecord createInventory(SeatInventoryRecord inv) {
        eventRepo.findById(inv.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (inv.getRemainingSeats() > inv.getTotalSeats()) {
            throw new BadRequestException("Remaining seats cannot exceed total seats");
        }
        return repo.save(inv);
    }

    @Override
    public SeatInventoryRecord getInventoryByEvent(Long eventId) {
        return repo.findByEventId(eventId)
                .orElseThrow(() -> new RuntimeException("Seat inventory not found"));
    }

    @Override
    public List<SeatInventoryRecord> getAllInventories() {
        return repo.findAll();
    }

    // ===== CONTROLLER METHODS =====

    @Override
    public SeatInventoryRecord save(SeatInventoryRecord inv) {
        return repo.save(inv);
    }

    @Override
    public List<SeatInventoryRecord> findAll() {
        return repo.findAll();
    }

    @Override
    public SeatInventoryRecord findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        // no-op
    }
}
