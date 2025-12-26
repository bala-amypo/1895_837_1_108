package com.example.demo.config;

import com.example.demo.repository.*;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryBeanConfig {

    @Bean
    public EventRecordRepository eventRecordRepository() {
        return Mockito.mock(EventRecordRepository.class);
    }

    @Bean
    public SeatInventoryRecordRepository seatInventoryRecordRepository() {
        return Mockito.mock(SeatInventoryRecordRepository.class);
    }

    @Bean
    public PricingRuleRepository pricingRuleRepository() {
        return Mockito.mock(PricingRuleRepository.class);
    }

    @Bean
    public DynamicPriceRecordRepository dynamicPriceRecordRepository() {
        return Mockito.mock(DynamicPriceRecordRepository.class);
    }

    @Bean
    public PriceAdjustmentLogRepository priceAdjustmentLogRepository() {
        return Mockito.mock(PriceAdjustmentLogRepository.class);
    }
}
