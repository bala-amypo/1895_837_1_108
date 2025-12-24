package com.example.demo.controller;

import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.service.DynamicPriceRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pricing")
public class DynamicPriceController {

    private final DynamicPriceRecordService pricingService;

    public DynamicPriceController(DynamicPriceRecordService pricingService) {
        this.pricingService = pricingService;
    }

    @GetMapping("/event/{eventId}")
    public List<DynamicPriceRecord> getPricesByEvent(@PathVariable Long eventId) {
        return pricingService.getPricesByEventId(eventId);
    }
}
