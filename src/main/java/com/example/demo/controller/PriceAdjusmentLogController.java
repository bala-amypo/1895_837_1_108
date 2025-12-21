package com.example.demo.controller;

import com.example.demo.model.PriceAdjustmentLog;
import com.example.demo.service.PriceAdjustmentLogService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/price-adjustments")
public class PriceAdjustmentLogController {

    private final PriceAdjustmentLogService service;

    public PriceAdjustmentLogController(PriceAdjustmentLogService service) {
        this.service = service;
    }

    @GetMapping
    public List<PriceAdjustmentLog> getAll() {
        return service.getAll();
    }

    @GetMapping("/{eventCode}")
    public List<PriceAdjustmentLog> getByEventCode(@PathVariable String eventCode) {
        return service.getLogs(eventCode);
    }
}
