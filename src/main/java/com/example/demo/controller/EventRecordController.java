package com.example.demo.controller;

import com.example.demo.model.EventRecord;
import com.example.demo.service.EventRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
public class EventRecordController {

    private final EventRecordService service;

    public EventRecordController(EventRecordService service) {
        this.service = service;
    }

    @PostMapping
    public EventRecord createEvent(@RequestBody EventRecord event) {
        return service.createEvent(event);
    }

    @GetMapping("/{id}")
    public EventRecord getEvent(@PathVariable Long id) {
        return service.getEventById(id);
    }

    @GetMapping
    public List<EventRecord> getAllEvents() {
        return service.getAllEvents();
    }

    @PatchMapping("/{id}/status")
    public Map<String, Object> updateEventStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {

        service.updateEventStatus(id, active);
        return Map.of(
                "eventId", id,
                "active", active,
                "message", "Event status updated"
        );
    }
}
