package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DynamicPricingEngineServiceImpl implements DynamicPricingEngineService {

    private final EventRecordRepository eventRepo;
    private final SeatInventoryRecordRepository seatRepo;
    private final PricingRuleRepository ruleRepo;
    private final DynamicPriceRecordRepository priceRepo;
    private final PriceAdjustmentLogRepository logRepo;

    public DynamicPricingEngineServiceImpl(
            EventRecordRepository eventRepo,
            SeatInventoryRepository seatRepo,
            PricingRuleRepository ruleRepo,
            DynamicPriceRecordRepository priceRepo,
            PriceAdjustmentLogRepository logRepo) {

        this.eventRepo = eventRepo;
        this.seatRepo = seatRepo;
        this.ruleRepo = ruleRepo;
        this.priceRepo = priceRepo;
        this.logRepo = logRepo;
    }

    @Override
    public DynamicPriceRecord computePrice(Long eventId) {

        EventRecord event = eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        SeatInventoryRecord seat = seatRepo.findByEventId(eventId)
                .orElseThrow(() -> new RuntimeException("Seat inventory not found"));

        double price = event.getBasePrice();
        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), event.getEventDate());

        List<PricingRule> rules = ruleRepo.findByActiveTrue();

        for (PricingRule rule : rules) {
            boolean seatMatch =
                    seat.getRemainingSeats() >= rule.getMinRemainingSeats()
                            && seat.getRemainingSeats() <= rule.getMaxRemainingSeats();

            boolean dayMatch = daysLeft <= rule.getDaysBeforeEvent();

            if (seatMatch && dayMatch) {
                price = price * rule.getPriceMultiplier();

                PriceAdjustmentLog log = new PriceAdjustmentLog();
                log.setEventId(event.getId());
                log.setEventCode(event.getEventCode());
                log.setOldPrice(event.getBasePrice());
                log.setNewPrice(price);
                log.setReason(rule.getRuleCode());
                log.setChangedAt(LocalDateTime.now());
                logRepo.save(log);
                break;
            }
        }

        DynamicPriceRecord record = new DynamicPriceRecord();
        record.setEventId(eventId);
        record.setPrice(price);
        record.setCalculatedAt(LocalDateTime.now());

        return priceRepo.save(record);
    }

    @Override
    public List<DynamicPriceRecord> getAllComputedPrices() {
        return priceRepo.findAll();
    }
}
