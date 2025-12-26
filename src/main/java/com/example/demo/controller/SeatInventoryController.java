package com.example.demo.controller;

import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.service.SeatInventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class SeatInventoryController {

    private final SeatInventoryService service;

    public SeatInventoryController(SeatInventoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SeatInventoryRecord> create(
            @RequestBody SeatInventoryRecord record) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createInventory(record));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<SeatInventoryRecord> getByEvent(
            @PathVariable Long eventId) {
        return ResponseEntity.ok(service.getInventoryByEvent(eventId));
    }
}
