package com.example.demo.service.impl;

import com.example.demo.model.PriceAdjustmentLog;
import com.example.demo.repository.PriceAdjustmentLogRepository;
import com.example.demo.service.PriceAdjustmentLogService;

import java.util.List;

public class PriceAdjustmentLogServiceImpl implements PriceAdjustmentLogService {

    private final PriceAdjustmentLogRepository logRepository;

    public PriceAdjustmentLogServiceImpl(PriceAdjustmentLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public List<PriceAdjustmentLog> getAdjustmentsByEvent(Long eventId) {
        return logRepository.findByEventId(eventId);
    }
}
