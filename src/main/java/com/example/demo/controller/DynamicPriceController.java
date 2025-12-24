package com.example.demo.controller;

import com.example.demo.model.DynamicPriceRecord;
import com.example.demo.repository.DynamicPriceRecordRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pricing")
public class DynamicPriceController {

    private final DynamicPriceRecordRepository priceRepository;

    public DynamicPriceController(DynamicPriceRecordRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @GetMapping("/event/{eventId}")
    public List<DynamicPriceRecord> getPricesByEvent(@PathVariable Long eventId) {
        return priceRepository.findAll()
                .stream()
                .filter(p -> p.getEventId().equals(eventId))
                .collect(Collectors.toList());
    }
}
