package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class SeatInventoryController {

    @GetMapping("/event/{eventId}")
    public String getInventoryByEvent(@PathVariable Long eventId) {
        return "Seat inventory fetched internally for eventId = " + eventId;
    }
}
