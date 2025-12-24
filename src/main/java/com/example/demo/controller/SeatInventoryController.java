package com.example.demo.controller;

import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.repository.SeatInventoryRecordRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
public class SeatInventoryController {

    private final SeatInventoryRecordRepository inventoryRepository;

    public SeatInventoryController(SeatInventoryRecordRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping("/event/{eventId}")
    public Optional<SeatInventoryRecord> getInventoryByEvent(@PathVariable Long eventId) {
        return inventoryRepository.findByEventId(eventId);
    }
}
