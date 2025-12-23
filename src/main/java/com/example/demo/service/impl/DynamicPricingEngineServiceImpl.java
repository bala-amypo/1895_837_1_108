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

@Service   // ⭐ THIS IS THE KEY FIX
public class DynamicPricingEngineServiceImpl implements DynamicPricingEngineService {

    private final EventRecordRepository eventRepo;
    private final SeatInventoryRecordRepository inventoryRepo;
    private final PricingRuleRepository ruleRepo;
    private final DynamicPriceRecordRepository priceRepo;
    private final PriceAdjustmentLogRepository logRepo;

    // ⭐ Spring will use this constructor
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

    // ================= TEST-USED METHODS =================

    @Override
    public DynamicPriceRecord computeDynamicPrice(Long eventId) {

        EventRecord event = eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (!Boolean.TRUE.equals(event.getActive())) {
            throw new BadRequestException("Event is not active");
        }

        SeatInventoryRecord inventory = inventoryRepo.findByEventId(eventId)
                .orElseThrow(() -> new RuntimeException("Seat inventory not found"));

        double price = event.getBasePrice();
        List<PricingRule> rules = ruleRepo.findByActiveTrue();

        long daysBeforeEvent =
                ChronoUnit.DAYS.between(LocalDate.now(), event.getEventDate());

        StringBuilder appliedRules = new StringBuilder();

        for (PricingRule rule : rules) {
            if (inventory.getRemainingSeats() >= rule.getMinRemainingSeats()
                    && inventory.getRemainingSeats() <= rule.getMaxRemainingSeats()
                    && daysBeforeEvent <= rule.getDaysBeforeEvent()) {

                price = price * rule.getPriceMultiplier();
                appliedRules.append(rule.getRuleCode()).append(",");
            }
        }

        Optional<DynamicPriceRecord> last =
                priceRepo.findFirstByEventIdOrderByComputedAtDesc(eventId);

        DynamicPriceRecord record = new DynamicPriceRecord();
        record.setEventId(eventId);
        record.setComputedPrice(price);
        record.setAppliedRuleCodes(appliedRules.toString());

        priceRepo.save(record);

        // log price change
        if (last.isPresent() && last.get().getComputedPrice() != price) {
            PriceAdjustmentLog log = new PriceAdjustmentLog();
            log.setEventId(eventId);
            log.setOldPrice(last.get().getComputedPrice());
            log.setNewPrice(price);
            logRepo.save(log);
        }

        return record;
    }

    @Override
    public List<DynamicPriceRecord> getPriceHistory(Long eventId) {
        return priceRepo.findByEventIdOrderByComputedAtDesc(eventId);
    }

    @Override
    public List<DynamicPriceRecord> getAllComputedPrices() {
        return priceRepo.findAll();
    }

    // ================= CONTROLLER-USED METHODS =================

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
