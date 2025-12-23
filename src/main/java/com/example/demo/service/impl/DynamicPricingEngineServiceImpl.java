package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DynamicPricingEngineServiceImpl implements DynamicPricingEngineService {

    private final EventRecordRepository eventRepo;
    private final SeatInventoryRecordRepository inventoryRepo;
    private final PricingRuleRepository ruleRepo;
    private final DynamicPriceRecordRepository priceRepo;
    private final PriceAdjustmentLogRepository logRepo;

    public DynamicPricingEngineServiceImpl(
            EventRecordRepository eventRepo,
            SeatInventoryRecordRepository inventoryRepo,
            PricingRuleRepository ruleRepo,
            DynamicPriceRecordRepository priceRepo,
            PriceAdjustmentLogRepository logRepo) {

        this.eventRepo = eventRepo;
        this.inventoryRepo = inventoryRepo;
        this.ruleRepo = ruleRepo;
        this.priceRepo = priceRepo;
        this.logRepo = logRepo;
    }

    @Override
    public DynamicPriceRecord computeDynamicPrice(Long eventId) {

        EventRecord event = eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (!Boolean.TRUE.equals(event.getActive())) {
            throw new BadRequestException("Event is not active");
        }

        SeatInventoryRecord inventory = inventoryRepo.findByEventId(eventId)
                .orElseThrow(() -> new RuntimeException("Seat inventory not found"));

        double calculatedPrice = event.getBasePrice();

        long daysLeft = ChronoUnit.DAYS.between(
                LocalDate.now(),
                event.getEventDate()
        );

        StringBuilder appliedRules = new StringBuilder();

        List<PricingRule> rules = ruleRepo.findByActiveTrue();
        for (PricingRule rule : rules) {
            if (inventory.getRemainingSeats() >= rule.getMinRemainingSeats()
                    && inventory.getRemainingSeats() <= rule.getMaxRemainingSeats()
                    && daysLeft <= rule.getDaysBeforeEvent()) {

                calculatedPrice = calculatedPrice * rule.getPriceMultiplier();
                appliedRules.append(rule.getRuleCode()).append(",");
            }
        }

        String appliedRuleCodes =
                appliedRules.length() > 0
                        ? appliedRules.substring(0, appliedRules.length() - 1)
                        : "";

        DynamicPriceRecord record = new DynamicPriceRecord();
        record.setEventId(eventId);
        record.setComputedPrice(calculatedPrice);
        record.setAppliedRuleCodes(appliedRuleCodes);

        priceRepo.findFirstByEventIdOrderByComputedAtDesc(eventId)
                .ifPresent(prev -> {
                    if (Double.compare(prev.getComputedPrice(), calculatedPrice) != 0) {
                        PriceAdjustmentLog log = new PriceAdjustmentLog();
                        log.setEventId(eventId);
                        log.setOldPrice(prev.getComputedPrice());
                        log.setNewPrice(calculatedPrice);
                        logRepo.save(log);
                    }
                });

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

    // Controller support
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
        return priceRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        priceRepo.deleteById(id);
    }
}