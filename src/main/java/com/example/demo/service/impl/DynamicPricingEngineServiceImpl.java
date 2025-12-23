package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DynamicPricingEngineService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class DynamicPricingEngineServiceImpl implements DynamicPricingEngineService {

    private final EventRecordRepository eventRepo;
    private final SeatInventoryRecordRepository invRepo;
    private final PricingRuleRepository ruleRepo;
    private final DynamicPriceRecordRepository priceRepo;
    private final PriceAdjustmentLogRepository logRepo;

    public DynamicPricingEngineServiceImpl(
            EventRecordRepository eventRepo,
            SeatInventoryRecordRepository invRepo,
            PricingRuleRepository ruleRepo,
            DynamicPriceRecordRepository priceRepo,
            PriceAdjustmentLogRepository logRepo) {

        this.eventRepo = eventRepo;
        this.invRepo = invRepo;
        this.ruleRepo = ruleRepo;
        this.priceRepo = priceRepo;
        this.logRepo = logRepo;
    }

    // ===== TEST METHODS =====

    @Override
    public DynamicPriceRecord computeDynamicPrice(Long eventId) {

        EventRecord event = eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (!event.getActive()) {
            throw new BadRequestException("Event is not active");
        }

        SeatInventoryRecord inv = invRepo.findByEventId(eventId)
                .orElseThrow(() -> new RuntimeException("Seat inventory not found"));

        List<PricingRule> rules = ruleRepo.findByActiveTrue();

        double multiplier = 1.0;
        StringBuilder applied = new StringBuilder();

        long days = ChronoUnit.DAYS.between(LocalDate.now(), event.getEventDate());

        for (PricingRule r : rules) {
            if (inv.getRemainingSeats() >= r.getMinRemainingSeats()
                    && inv.getRemainingSeats() <= r.getMaxRemainingSeats()
                    && days <= r.getDaysBeforeEvent()) {

                if (r.getPriceMultiplier() > multiplier) {
                    multiplier = r.getPriceMultiplier();
                }
                applied.append(r.getRuleCode()).append(",");
            }
        }

        double price = event.getBasePrice() * multiplier;

        DynamicPriceRecord record = new DynamicPriceRecord();
        record.setEventId(eventId);
        record.setComputedPrice(price);
        record.setAppliedRuleCodes(applied.toString());

        Optional<DynamicPriceRecord> prev =
                priceRepo.findFirstByEventIdOrderByComputedAtDesc(eventId);

        if (prev.isPresent() && !prev.get().getComputedPrice().equals(price)) {
            PriceAdjustmentLog log = new PriceAdjustmentLog();
            log.setEventId(eventId);
            log.setOldPrice(prev.get().getComputedPrice());
            log.setNewPrice(price);
            logRepo.save(log);
        }

        return priceRepo.save(record);
    }

    @Override
    public List<DynamicPriceRecord> getPriceHistory(Long eventId) {
        return priceRepo.findByEventIdOrderByComputedAtDesc(eventId);
    }

    @Override
    public List<DynamicPriceRecord> getAllComputedPrices() {
        return priceRepo.findAll();
    }

    // ===== CONTROLLER METHODS =====

    @Override
    public DynamicPriceRecord save(DynamicPriceRecord record) {
        return priceRepo.save(record);
    }

    @Override
    public List<DynamicPriceRecord> findAll() {
        return priceRepo.findAll();
    }

    @Override
    public DynamicPriceRecord findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        // no-op
    }
}
