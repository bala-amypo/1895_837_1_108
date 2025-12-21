package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.service.SeatInventoryService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatInventoryController {

    private final SeatInventoryService service;

    public SeatInventoryController(SeatInventoryService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<SeatInventoryRecord> create(@RequestBody SeatInventoryRecord record) {
        return new ApiResponse<>(true, "Seat inventory created", service.create(record));
    }

    @GetMapping
    public ApiResponse<List<SeatInventoryRecord>> getAll() {
        return new ApiResponse<>(true, "Seat inventory list", service.getAll());
    }

    @GetMapping("/event/{eventId}")
    public ApiResponse<SeatInventoryRecord> getByEvent(@PathVariable Long eventId) {
        return new ApiResponse<>(true, "Seat inventory fetched",
                service.getByEventId(eventId));
    }
}
