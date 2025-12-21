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

    @GetMapping("/event/{eventCode}")
    public List<PriceAdjustmentLog> getLogs(@PathVariable String eventCode) {
        return service.getLogs(eventCode);
    }

    @GetMapping
    public List<PriceAdjustmentLog> getAll() {
        return service.getAll();
    }
}
