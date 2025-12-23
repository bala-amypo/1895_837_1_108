package com.example.demo.service.impl;

import com.example.demo.model.PriceAdjustmentLog;
import com.example.demo.repository.PriceAdjustmentLogRepository;
import com.example.demo.service.PriceAdjustmentLogService;

import java.util.List;

public class PriceAdjustmentLogServiceImpl implements PriceAdjustmentLogService {

    private final PriceAdjustmentLogRepository repo;

    public PriceAdjustmentLogServiceImpl(PriceAdjustmentLogRepository repo) {
        this.repo = repo;
    }

    // ===== TEST METHOD =====

    @Override
    public List<PriceAdjustmentLog> getAdjustmentsByEvent(Long eventId) {
        return repo.findByEventId(eventId);
    }

    // ===== CONTROLLER METHODS =====

    @Override
    public PriceAdjustmentLog save(PriceAdjustmentLog log) {
        return repo.save(log);
    }

    @Override
    public List<PriceAdjustmentLog> findAll() {
        return List.of();
    }

    @Override
    public PriceAdjustmentLog findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        // no-op
    }
}
