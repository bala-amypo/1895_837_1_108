package com.example.demo.controller;

import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.service.SeatInventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class SeatInventoryController {

    private final SeatInventoryService seatInventoryService;

    public SeatInventoryController(SeatInventoryService seatInventoryService) {
        this.seatInventoryService = seatInventoryService;
    }

    @PostMapping
    public SeatInventoryRecord createInventory(@RequestBody SeatInventoryRecord record) {
        return seatInventoryService.createInventory(record);
    }

    @GetMapping("/event/{eventId}")
    public SeatInventoryRecord getInventoryByEvent(@PathVariable Long eventId) {
        return seatInventoryService.getInventoryByEvent(eventId);
    }
}
