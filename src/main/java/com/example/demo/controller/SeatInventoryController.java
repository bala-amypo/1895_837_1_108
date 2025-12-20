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
    public SeatInventoryRecord create(@RequestBody SeatInventoryRecord s) {
        return service.create(s);
    }

    @GetMapping
    public List<SeatInventoryRecord> getAll() {
        return service.getAll();
    }

    @GetMapping("/event/{eventCode}")
    public SeatInventoryRecord getByEvent(@PathVariable String eventCode) {
        return service.getByEvent(eventCode);
}

    
}
