package com.example.demo.controller;

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
    public SeatInventoryRecord save(@RequestBody SeatInventoryRecord record) {
        return service.save(record);
    }

    @GetMapping
    public List<SeatInventoryRecord> getAll() {
        return service.findAll();
    }

    @GetMapping("/event/{eventId}")
    public SeatInventoryRecord getByEvent(@PathVariable Long eventId) {
        return service.findByEventId(eventId);
    }
}
