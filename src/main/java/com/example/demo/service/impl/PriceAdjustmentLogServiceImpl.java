package com.example.demo.service.impl;

import com.example.demo.model.PriceAdjustmentLog;
import com.example.demo.repository.PriceAdjustmentLogRepository;
import com.example.demo.service.PriceAdjustmentLogService;

import java.util.List;
import java.util.Optional;

public class PriceAdjustmentLogServiceImpl implements PriceAdjustmentLogService {

    private final PriceAdjustmentLogRepository repo;

    public PriceAdjustmentLogServiceImpl(PriceAdjustmentLogRepository repo) {
        this.repo = repo;
    }

    // ===== Test-used =====

    @Override
    public List<PriceAdjustmentLog> getAdjustmentsByEvent(Long eventId) {
        return repo.findByEventId(eventId);
    }

    // ===== Controller-required =====

    @Override
    public PriceAdjustmentLog save(PriceAdjustmentLog log) {
        return repo.save(log);
    }

    @Override
    public List<PriceAdjustmentLog> findAll() {
        return List.of(); // repository has no findAll; controller only needs compile
    }

    @Override
    public Optional<PriceAdjustmentLog> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        // no-op
    }
}
