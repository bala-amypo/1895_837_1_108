package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.repository.EventRecordRepository;
import com.example.demo.repository.SeatInventoryRecordRepository;
import com.example.demo.service.SeatInventoryService;

public class SeatInventoryServiceImpl implements SeatInventoryService {

    private final SeatInventoryRecordRepository inventoryRepository;
    private final EventRecordRepository eventRepository;

    public SeatInventoryServiceImpl(
            SeatInventoryRecordRepository inventoryRepository,
            EventRecordRepository eventRepository
    ) {
        this.inventoryRepository = inventoryRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public SeatInventoryRecord createInventory(SeatInventoryRecord record) {

        eventRepository.findById(record.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (record.getRemainingSeats() > record.getTotalSeats()) {
            throw new BadRequestException("Remaining seats cannot exceed total seats");
        }

        return inventoryRepository.save(record);
    }

    @Override
    public SeatInventoryRecord getInventoryByEvent(Long eventId) {
        return inventoryRepository.findByEventId(eventId)
                .orElseThrow(() -> new RuntimeException("Seat inventory not found"));
    }
}
