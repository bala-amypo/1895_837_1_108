package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.EventRecord;
import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.repository.EventRecordRepository;
import com.example.demo.repository.SeatInventoryRecordRepository;
import com.example.demo.service.SeatInventoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatInventoryServiceImpl implements SeatInventoryService {

    private final SeatInventoryRecordRepository invRepo;
    private final EventRecordRepository eventRepo;

    public SeatInventoryServiceImpl(SeatInventoryRecordRepository invRepo,
                                    EventRecordRepository eventRepo) {
        this.invRepo = invRepo;
        this.eventRepo = eventRepo;
    }

    @Override
    public SeatInventoryRecord createInventory(SeatInventoryRecord inv) {

        EventRecord event = eventRepo.findById(inv.getEventId())
                .orElseThrow(() -> new BadRequestException("Event not found"));

        if (inv.getRemainingSeats() > inv.getTotalSeats()) {
            throw new BadRequestException("Remaining seats cannot exceed total seats");
        }

        return invRepo.save(inv);
    }

    @Override
    public SeatInventoryRecord updateRemainingSeats(Long eventId, Integer remaining) {

        SeatInventoryRecord inv = invRepo.findByEventId(eventId)
                .orElseThrow(() -> new BadRequestException("Seat inventory not found"));

        if (remaining > inv.getTotalSeats()) {
            throw new BadRequestException("Remaining seats cannot exceed total seats");
        }

        inv.setRemainingSeats(remaining);

        return invRepo.save(inv);
    }

    @Override
    public Optional<SeatInventoryRecord> getInventoryByEvent(Long eventId) {
        return invRepo.findByEventId(eventId);
    }

    @Override
    public List<SeatInventoryRecord> getAllInventories() {
        return invRepo.findAll();
    }
}
