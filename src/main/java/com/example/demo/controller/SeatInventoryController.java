package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
public class SeatInventoryController {

    @GetMapping("/event/{eventId}")
    public String getInventoryByEvent(@PathVariable Long eventId) {
        return "Fetched seat inventory for eventId = " + eventId;
    }

    @PostMapping
    public String createInventory(@RequestBody Map<String, Object> inventory) {

        Long eventId = Long.valueOf(inventory.get("eventId").toString());
        Integer totalSeats = Integer.valueOf(inventory.get("totalSeats").toString());
        Integer remainingSeats = Integer.valueOf(inventory.get("remainingSeats").toString());

        return "Seat inventory created: " +
                "eventId=" + eventId +
                ", totalSeats=" + totalSeats +
                ", remainingSeats=" + remainingSeats;
    }
}
