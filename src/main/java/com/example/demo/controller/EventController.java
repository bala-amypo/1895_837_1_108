package com.example.demo.controller;

import com.example.demo.model.EventRecord;
import com.example.demo.service.EventRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRecordService service;

    public EventController(EventRecordService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EventRecord> create(@RequestBody EventRecord event) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createEvent(event));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEventById(id));
    }

    @GetMapping
    public ResponseEntity<List<EventRecord>> getAll() {
        return ResponseEntity.ok(service.getAllEvents());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<EventRecord> updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {
        return ResponseEntity.ok(service.updateEventStatus(id, active));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<EventRecord> getByCode(@PathVariable String code) {
        return service.getEventByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
