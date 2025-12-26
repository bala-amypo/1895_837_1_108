package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DynamicPricingEngineService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
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
            PriceAdjustmentLogRepository logRepository
    ) {
        this.eventRepository = eventRepository;
        this.inventoryRepository = inventoryRepository;
        this.ruleRepository = ruleRepository;
        this.priceRepository = priceRepository;
        this.logRepository = logRepository;
    }

    @Override
    public DynamicPriceRecord computeDynamicPrice(Long eventId) {

        // 1. Fetch event
        EventRecord event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // 2. Validate event
        if (!Boolean.TRUE.equals(event.getActive())) {
            throw new BadRequestException("Event is not active");
        }

        // 3. Fetch inventory
        SeatInventoryRecord inventory = inventoryRepository.findByEventId(eventId)
                .orElseThrow(() -> new RuntimeException("Seat inventory not found"));

        double finalPrice = event.getBasePrice();
        String appliedRules = "";

        // ✅ FIX 1: Correct day calculation
        long daysBeforeEvent = ChronoUnit.DAYS.between(
                LocalDate.now(),
                event.getEventDate()
        );

        // 4. Apply pricing rules
        for (PricingRule rule : ruleRepository.findByActiveTrue()) {

            if (inventory.getRemainingSeats() >= rule.getMinRemainingSeats()
                    && inventory.getRemainingSeats() <= rule.getMaxRemainingSeats()
                    // ✅ FIX 2: Correct comparison
                    && daysBeforeEvent <= rule.getDaysBeforeEvent()) {

                finalPrice = event.getBasePrice() * rule.getPriceMultiplier();
                appliedRules = rule.getRuleCode();
                break;
            }
        }

        // 5. Log price adjustment if changed
        Optional<DynamicPriceRecord> previous =
                priceRepository.findFirstByEventIdOrderByComputedAtDesc(eventId);

        if (previous.isPresent()
                && !previous.get().getComputedPrice().equals(finalPrice)) {

            PriceAdjustmentLog log = new PriceAdjustmentLog();
            log.setEventId(eventId);
            log.setOldPrice(previous.get().getComputedPrice());
            log.setNewPrice(finalPrice);
            logRepository.save(log);
        }

        // 6. Save computed price
        DynamicPriceRecord record = new DynamicPriceRecord();
        record.setEventId(eventId);
        record.setComputedPrice(finalPrice);
        record.setAppliedRuleCodes(appliedRules);

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
