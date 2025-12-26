package com.example.demo.config;

import com.example.demo.repository.*;
import com.example.demo.service.*;
import com.example.demo.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfig {

    /* ===================== SERVICES ===================== */

    @Bean
    public EventRecordService eventRecordService(
            EventRecordRepository eventRecordRepository) {
        return new EventRecordServiceImpl(eventRecordRepository);
    }

    @Bean
    public SeatInventoryService seatInventoryService(
            SeatInventoryRecordRepository seatInventoryRecordRepository,
            EventRecordRepository eventRecordRepository) {
        return new SeatInventoryServiceImpl(
                seatInventoryRecordRepository,
                eventRecordRepository
        );
    }

    @Bean
    public PricingRuleService pricingRuleService(
            PricingRuleRepository pricingRuleRepository) {
        return new PricingRuleServiceImpl(pricingRuleRepository);
    }

    @Bean
    public PriceAdjustmentLogService priceAdjustmentLogService(
            PriceAdjustmentLogRepository priceAdjustmentLogRepository) {
        return new PriceAdjustmentLogServiceImpl(priceAdjustmentLogRepository);
    }

    @Bean
    public DynamicPricingEngineService dynamicPricingEngineService(
            EventRecordRepository eventRecordRepository,
            SeatInventoryRecordRepository seatInventoryRecordRepository,
            PricingRuleRepository pricingRuleRepository,
            DynamicPriceRecordRepository dynamicPriceRecordRepository,
            PriceAdjustmentLogRepository priceAdjustmentLogRepository) {

        return new DynamicPricingEngineServiceImpl(
                eventRecordRepository,
                seatInventoryRecordRepository,
                pricingRuleRepository,
                dynamicPriceRecordRepository,
                priceAdjustmentLogRepository
        );
    }
}
