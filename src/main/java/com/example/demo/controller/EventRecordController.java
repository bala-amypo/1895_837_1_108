package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
public class EventRecordController {

    @PostMapping
    public Map<String, Object> createEvent(
            @RequestBody Map<String, Object> event
    ) {
        return Map.of(
                "message", "Event created successfully",
                "event", event
        );
    }

    @GetMapping
    public List<Map<String, Object>> getAllEvents() {
        return List.of();
    }

    @GetMapping("/{id}")
    public Map<String, Object> getEventById(
            @PathVariable Long id
    ) {
        return Map.of(
                "eventId", id,
                "message", "Event fetched successfully"
        );
    }

    @PatchMapping("/{id}/status")
    public Map<String, Object> updateEventStatus(
            @PathVariable Long id,
            @RequestParam boolean active
    ) {
        return Map.of(
                "eventId", id,
                "active", active,
                "message", "Event status updated"
        );
    }
}
