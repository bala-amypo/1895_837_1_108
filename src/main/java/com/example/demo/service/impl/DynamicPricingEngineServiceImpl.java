package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DynamicPricingEngineService;

import java.time.temporal.ChronoUnit;
import java.util.*;

public class DynamicPricingEngineServiceImpl implements DynamicPricingEngineService {

    private final EventRecordRepository eventRepo;
    private final SeatInventoryRecordRepository invRepo;
    private final PricingRuleRepository ruleRepo;
    private final DynamicPriceRecordRepository priceRepo;
    private final PriceAdjustmentLogRepository logRepo;

    public DynamicPricingEngineServiceImpl(
            EventRecordRepository e,
            SeatInventoryRecordRepository i,
            PricingRuleRepository r,
            DynamicPriceRecordRepository p,
            PriceAdjustmentLogRepository l) {
        eventRepo = e;
        invRepo = i;
        ruleRepo = r;
        priceRepo = p;
        logRepo = l;
    }

    public DynamicPriceRecord computeDynamicPrice(Long eventId) {
        EventRecord event = eventRepo.findById(eventId).orElseThrow();
        if (!event.getActive())
            throw new BadRequestException("Event is not active");

        SeatInventoryRecord inv =
                invRepo.findByEventId(eventId)
                        .orElseThrow(() -> new RuntimeException("Seat inventory not found"));

        double price = event.getBasePrice();
        List<PricingRule> rules = ruleRepo.findByActiveTrue();
        List<String> applied = new ArrayList<>();

        long days = ChronoUnit.DAYS.between(java.time.LocalDate.now(), event.getEventDate());

        for (PricingRule r : rules) {
            if (inv.getRemainingSeats() >= r.getMinRemainingSeats()
                    && inv.getRemainingSeats() <= r.getMaxRemainingSeats()
                    && days <= r.getDaysBeforeEvent()) {
                price *= r.getPriceMultiplier();
                applied.add(r.getRuleCode());
                break;
            }
        }

        DynamicPriceRecord rec = new DynamicPriceRecord();
        rec.setEventId(eventId);
        rec.setComputedPrice(price);
        rec.setAppliedRuleCodes(String.join(",", applied));
        rec.prePersist();

        Optional<DynamicPriceRecord> prev =
                priceRepo.findFirstByEventIdOrderByComputedAtDesc(eventId);

        if (prev.isPresent() && !prev.get().getComputedPrice().equals(price)) {
            PriceAdjustmentLog log = new PriceAdjustmentLog();
            log.setEventId(eventId);
            log.setOldPrice(prev.get().getComputedPrice());
            log.setNewPrice(price);
            log.prePersist();
            logRepo.save(log);
        }

        return priceRepo.save(rec);
    }

    public List<DynamicPriceRecord> getPriceHistory(Long eventId) {
        return priceRepo.findByEventIdOrderByComputedAtDesc(eventId);
    }

    public List<DynamicPriceRecord> getAllComputedPrices() {
        return priceRepo.findAll();
    }
}
