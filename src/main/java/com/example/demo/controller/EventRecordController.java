package com.example.demo.controller;

import com.example.demo.model.EventRecord;
import com.example.demo.service.EventRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventRecordController {

    private final EventRecordService eventService;

    public EventRecordController(EventRecordService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventRecord> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public EventRecord getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }
}
