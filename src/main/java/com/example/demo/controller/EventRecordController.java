package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
public class EventRecordController {

    @GetMapping
    public String getAllEvents() {
        return "Fetched all events";
    }

    @GetMapping("/{id}")
    public String getEventById(@PathVariable Long id) {
        return "Fetched event with id = " + id;
    }

    @PostMapping
    public String createEvent(@RequestBody Map<String, Object> event) {

        String eventCode = (String) event.get("eventCode");
        String eventName = (String) event.get("eventName");
        String venue = (String) event.get("venue");
        String eventDate = (String) event.get("eventDate");
        Double basePrice = event.get("basePrice") != null
                ? Double.valueOf(event.get("basePrice").toString())
                : null;
        Boolean active = (Boolean) event.getOrDefault("active", true);

        return "Event created successfully: " +
                "eventCode=" + eventCode +
                ", eventName=" + eventName +
                ", venue=" + venue +
                ", eventDate=" + eventDate +
                ", basePrice=" + basePrice +
                ", active=" + active;
    }
}
