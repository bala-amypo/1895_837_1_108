package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
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

    @GetMapping("/{eventCode}")
    public ApiResponse<List<PriceAdjustmentLog>> getLogs(@PathVariable String eventCode) {
        return new ApiResponse<>(true, "Logs fetched",
                service.getLogs(eventCode));
    }
}
