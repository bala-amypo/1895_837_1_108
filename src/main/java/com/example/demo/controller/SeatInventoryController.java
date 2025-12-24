package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
public class SeatInventoryController {

    @PostMapping
    public Map<String, Object> createInventory(
            @RequestBody Map<String, Object> inventory
    ) {
        return Map.of(
                "message", "Seat inventory created successfully",
                "inventory", inventory
        );
    }

    @GetMapping("/event/{eventId}")
    public Map<String, Object> getInventoryByEvent(
            @PathVariable Long eventId
    ) {
        return Map.of(
                "eventId", eventId,
                "totalSeats", 0,
                "remainingSeats", 0
        );
    }

    @GetMapping
    public List<Map<String, Object>> getAllInventories() {
        return List.of();
    }
}
