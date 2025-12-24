package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/pricing")
public class DynamicPriceController {

    @GetMapping("/event/{eventId}")
    public List<String> getPricesByEvent(@PathVariable Long eventId) {
        return Collections.singletonList(
                "Dynamic pricing calculated internally for eventId = " + eventId
        );
    }
}
