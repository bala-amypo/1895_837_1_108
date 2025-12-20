package com.example.demo.controller;

import com.example.demo.model.PriceAdjustmentLog;
import com.example.demo.service.PriceAdjustmentLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/price-adjustment")
public class PriceAdjustmentLogController {

    private final PriceAdjustmentLogService service;

    public PriceAdjustmentLogController(PriceAdjustmentLogService service) {
        this.service = service;
    }

    @GetMapping("/logs/{eventCode}")
    public List<PriceAdjustmentLog> getLogs(@PathVariable String eventCode) {
        return service.getLogs(eventCode);
    }

    @GetMapping("/all")
    public List<PriceAdjustmentLog> getAllLogs() {
        return service.getAll();
    }
}
