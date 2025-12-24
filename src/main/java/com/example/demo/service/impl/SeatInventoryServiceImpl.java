package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.repository.*;
import com.example.demo.service.SeatInventoryService;

public class SeatInventoryServiceImpl implements SeatInventoryService {

    private final SeatInventoryRecordRepository repo;
    private final EventRecordRepository eventRepo;

    public SeatInventoryServiceImpl(SeatInventoryRecordRepository r, EventRecordRepository e) {
        repo = r;
        eventRepo = e;
    }

    public SeatInventoryRecord createInventory(SeatInventoryRecord inv) {
        eventRepo.findById(inv.getEventId()).orElseThrow();
        if (inv.getRemainingSeats() > inv.getTotalSeats())
            throw new BadRequestException("Remaining seats cannot exceed total seats");
        return repo.save(inv);
    }

    public SeatInventoryRecord getInventoryByEvent(Long eventId) {
        return repo.findByEventId(eventId).orElseThrow();
    }
}
