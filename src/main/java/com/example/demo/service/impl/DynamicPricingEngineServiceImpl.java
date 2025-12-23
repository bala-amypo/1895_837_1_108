package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DynamicPricingEngineService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class DynamicPricingEngineServiceImpl implements DynamicPricingEngineService {

    private final EventRecordRepository eventRepository;
    private final SeatInventoryRecordRepository inventoryRepository;
    private final PricingRuleRepository ruleRepository;
    private final DynamicPriceRecordRepository priceRepository;
    private final PriceAdjustmentLogRepository logRepository;

    public DynamicPricingEngineServiceImpl(
            EventRecordRepository eventRepository,
            SeatInventoryRecordRepository inventoryRepository,
            PricingRuleRepository ruleRepository,
            DynamicPriceRecordRepository priceRepository,
            PriceAdjustmentLogRepository logRepository) {

        this.eventRepository = eventRepository;
        this.inventoryRepository = inventoryRepository;
        this.ruleRepository = ruleRepository;
        this.priceRepository = priceRepository;
        this.logRepository = logRepository;
    }

    @Override
    public DynamicPriceRecord computeDynamicPrice(Long eventId) {

        EventRecord event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (!event.getActive()) {
            throw new BadRequestException("Event is not active");
        }

        SeatInventoryRecord inventory = inventoryRepository.findByEventId(eventId)
                .orElseThrow(() -> new RuntimeException("Seat inventory not found"));

        List<PricingRule> rules = ruleRepository.findByActiveTrue();

        double multiplier = 1.0;
        StringBuilder appliedRules = new StringBuilder();

        long daysToEvent =
                ChronoUnit.DAYS.between(LocalDate.now(), event.getEventDate());

        for (PricingRule rule : rules) {

            boolean seatMatch =
                    inventory.getRemainingSeats() >= rule.getMinRemainingSeats()
                            && inventory.getRemainingSeats() <= rule.getMaxRemainingSeats();

            boolean dayMatch = daysToEvent <= rule.getDaysBeforeEvent();

            if (seatMatch && dayMatch) {
                if (rule.getPriceMultiplier() > multiplier) {
                    multiplier = rule.getPriceMultiplier();
                }
                appliedRules.append(rule.getRuleCode()).append(",");
            }
        }

        double computedPrice = event.getBasePrice() * multiplier;

        DynamicPriceRecord record = new DynamicPriceRecord();
        record.setEventId(eventId);
        record.setComputedPrice(computedPrice);
        record.setAppliedRuleCodes(appliedRules.toString());

        Optional<DynamicPriceRecord> previous =
                priceRepository.findFirstByEventIdOrderByComputedAtDesc(eventId);

        if (previous.isPresent()
                && !previous.get().getComputedPrice().equals(computedPrice)) {

            PriceAdjustmentLog log = new PriceAdjustmentLog();
            log.setEventId(eventId);
            log.setOldPrice(previous.get().getComputedPrice());
            log.setNewPrice(computedPrice);
            logRepository.save(log);
        }

        return priceRepository.save(record);
    }

    @Override
    public List<DynamicPriceRecord> getPriceHistory(Long eventId) {
        return priceRepository.findByEventIdOrderByComputedAtDesc(eventId);
    }

    @Override
    public List<DynamicPriceRecord> getAllComputedPrices() {
        return priceRepository.findAll();
    }
}
