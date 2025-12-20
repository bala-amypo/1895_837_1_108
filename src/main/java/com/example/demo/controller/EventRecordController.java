package com.example.demo.controller;

import com.example.demo.model.EventRecord;
import com.example.demo.service.EventRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventRecordController {

    private final EventRecordService service;

    public EventRecordController(EventRecordService service) {
        this.service = service;
    }

    @PostMapping
    public EventRecord create(@RequestBody EventRecord r) {
        return service.create(r);
    }

    @GetMapping
    public List<EventRecord> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public EventRecord getOne(@PathVariable Long id) {
        return service.getOne(id);
    }
}
