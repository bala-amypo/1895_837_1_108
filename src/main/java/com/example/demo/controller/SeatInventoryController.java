package com.example.demo.controller;

import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.service.SeatInventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class SeatInventoryController {

    private final SeatInventoryService service;

    public SeatInventoryController(SeatInventoryService service) {
        this.service = service;
    }

    @PostMapping
    public SeatInventoryRecord createInventory(
            @RequestBody SeatInventoryRecord inventory) {
        return service.createInventory(inventory);
    }

    @GetMapping("/{eventId}")
    public SeatInventoryRecord getInventory(
            @PathVariable Long eventId) {
        return service.getInventoryByEvent(eventId);
    }
}
