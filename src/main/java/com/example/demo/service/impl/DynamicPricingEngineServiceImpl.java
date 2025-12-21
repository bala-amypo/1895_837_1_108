package com.example.demo.service.impl;

import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.model.EventRecord;
import com.example.demo.model.PricingRule;
import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.repository.DynamicPriceRecordRepository;
import com.example.demo.repository.EventRecordRepository;
import com.example.demo.repository.PricingRuleRepository;
import com.example.demo.repository.SeatInventoryRecordRepository;
import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DynamicPricingEngineServiceImpl implements DynamicPricingEngineService {

    private final DynamicPriceRecordRepository dynamicRepo;
    private final EventRecordRepository eventRepo;
    private final SeatInventoryRecordRepository seatRepo;
    private final PricingRuleRepository ruleRepo;

    public DynamicPricingEngineServiceImpl(
            DynamicPriceRecordRepository dynamicRepo,
            EventRecordRepository eventRepo,
            SeatInventoryRecordRepository seatRepo,
            PricingRuleRepository ruleRepo
    ) {
        this.dynamicRepo = dynamicRepo;
        this.eventRepo = eventRepo;
        this.seatRepo = seatRepo;
        this.ruleRepo = ruleRepo;
    }

    @Override
    public DynamicPriceRecord computePrice(Long eventId) {

        EventRecord event = eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("EVENT_NOT_FOUND"));

        SeatInventoryRecord inventory = seatRepo.findByEventId(eventId)
                .orElseThrow(() -> new RuntimeException("INVENTORY_NOT_FOUND"));

        List<PricingRule> rules = ruleRepo.findActiveRules();
        if (rules.isEmpty()) {
            throw new RuntimeException("NO_RULES_ACTIVE");
        }

        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), event.getEventDate());
        if (daysLeft < 0) {
            throw new RuntimeException("EVENT_EXPIRED");
        }

        double remainingRatio = (double) inventory.getRemainingSeats() / inventory.getTotalSeats();
        double price = event.getBasePrice();

        for (PricingRule rule : rules) {

            boolean matchSeat = remainingRatio <= rule.getSeatThreshold();
            boolean matchDay = daysLeft <= rule.getDayThreshold();

            if (matchSeat && matchDay) {
                price = price * rule.getMultiplier();
            }
        }

        DynamicPriceRecord newRecord = new DynamicPriceRecord();
        newRecord.setEventId(eventId);
        newRecord.setComputedPrice(price);
        newRecord.setSeatsRemaining(inventory.getRemainingSeats());
        newRecord.setEventDate(event.getEventDate());
        newRecord.setCreatedAt(LocalDate.now());

        return dynamicRepo.save(newRecord);
    }

    @Override
    public List<DynamicPriceRecord> getHistory(Long eventId) {
        return dynamicRepo.findByEventId(eventId);
    }
}
