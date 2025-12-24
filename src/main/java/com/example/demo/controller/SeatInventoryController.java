package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
public class SeatInventoryController {

    @GetMapping("/event/{eventId}")
    public String getInventoryByEvent(@PathVariable Long eventId) {
        return "Seat inventory fetched for eventId = " + eventId;
    }

    // ✅ POST
    @PostMapping
    public String addInventory(@RequestBody Map<String, Object> inventoryData) {
        return "Seat inventory added: " + inventoryData;
    }
}
