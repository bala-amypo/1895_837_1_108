package com.example.demo.controller;

import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.service.SeatInventoryRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class SeatInventoryController {

    private final SeatInventoryRecordService inventoryService;

    public SeatInventoryController(SeatInventoryRecordService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/event/{eventId}")
    public List<SeatInventoryRecord> getInventoryByEvent(@PathVariable Long eventId) {
        return inventoryService.getInventoryByEventId(eventId);
    }
}
