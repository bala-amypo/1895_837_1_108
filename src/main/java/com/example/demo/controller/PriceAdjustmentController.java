package com.example.demo.controller;

import com.example.demo.model.PriceAdjustmentLog;
import com.example.demo.service.PriceAdjustmentLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adjustments")
public class PriceAdjustmentController {

    private final PriceAdjustmentLogService service;

    public PriceAdjustmentController(PriceAdjustmentLogService service) {
        this.service = service;
    }

    @GetMapping("/{eventCode}")
    public List<PriceAdjustmentLog> history(@PathVariable String eventCode) {
        return service.getLogs(eventCode);
    }
}
