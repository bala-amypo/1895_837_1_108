package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DynamicPricingEngineServiceImpl
        implements DynamicPricingEngineService {

    private final EventRecordRepository eventRepo;
    private final SeatInventoryRecordRepository seatRepo;
    private final DynamicPriceRecordRepository priceRepo;

    public DynamicPricingEngineServiceImpl(
            EventRecordRepository eventRepo,
            SeatInventoryRecordRepository seatRepo,
            DynamicPriceRecordRepository priceRepo) {

        this.eventRepo = eventRepo;
        this.seatRepo = seatRepo;
        this.priceRepo = priceRepo;
    }

    @Override
    public DynamicPriceRecord computeDynamicPrice(Long eventId) {

        EventRecord event = eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        SeatInventoryRecord seat = seatRepo.findByEventId(eventId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        double price = event.getBasePrice();

        if (seat.getRemainingSeats() < 50) {
            price *= 1.2;
        }

        DynamicPriceRecord record = new DynamicPriceRecord();
        record.setEventId(eventId);
        record.setComputedPrice(price);
        record.setComputedAt(LocalDateTime.now());

        return priceRepo.save(record);
    }

    @Override
    public List<DynamicPriceRecord> getPriceHistory(Long eventId) {
        return priceRepo.findByEventIdOrderByComputedAtDesc(eventId);
    }
}
