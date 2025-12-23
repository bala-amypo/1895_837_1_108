package com.example.demo.service;

import com.example.demo.model.PriceAdjustmentLog;

import java.util.List;

public interface PriceAdjustmentLogService {

    // ===== Used by TESTS =====
    List<PriceAdjustmentLog> getAdjustmentsByEvent(Long eventId);

    // ===== Used by CONTROLLERS =====
    PriceAdjustmentLog save(PriceAdjustmentLog log);
    List<PriceAdjustmentLog> findAll();
    PriceAdjustmentLog findById(Long id);
    void deleteById(Long id);
}
