package com.example.demo.controller;

import com.example.demo.model.EventRecord;
import com.example.demo.service.EventRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventRecordController {

    private final EventRecordService eventRecordService;

    public EventRecordController(EventRecordService eventRecordService) {
        this.eventRecordService = eventRecordService;
    }

    @PostMapping
    public EventRecord createEvent(@RequestBody EventRecord event) {
        return eventRecordService.createEvent(event);
    }

    @GetMapping
    public List<EventRecord> getAllEvents() {
        return eventRecordService.getAllEvents();
    }

    @GetMapping("/{id}")
    public EventRecord getEventById(@PathVariable Long id) {
        return eventRecordService.getEventById(id);
    }

    @PatchMapping("/{id}/status")
    public EventRecord updateEventStatus(
            @PathVariable Long id,
            @RequestParam boolean active
    ) {
        return eventRecordService.updateEventStatus(id, active);
    }
}
