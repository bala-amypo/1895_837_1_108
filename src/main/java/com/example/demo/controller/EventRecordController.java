package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/events")
public class EventRecordController {

    @GetMapping
    public String getAllEvents() {
        return "Events fetched internally";
    }

    @GetMapping("/{id}")
    public String getEventById(@PathVariable Long id) {
        return "Event details fetched internally for id = " + id;
    }

    // ✅ POST endpoint
    @PostMapping
    public String createEvent(@RequestBody Map<String, Object> eventData) {
        return "Event created successfully: " + eventData;
    }
}
