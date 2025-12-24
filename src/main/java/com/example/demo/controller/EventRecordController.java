package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventRecordController {

    @GetMapping
    public List<String> getAllEvents() {
        return Collections.singletonList("Events fetched internally");
    }

    @GetMapping("/{id}")
    public String getEventById(@PathVariable Long id) {
        return "Event details fetched internally for id = " + id;
    }
}
