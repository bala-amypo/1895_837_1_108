package com.example.demo.service;

import com.example.demo.model.PriceAdjustmentLog;
import java.util.List;
import java.util.Optional;

public interface PriceAdjustmentLogService {

    // test-used
    List<PriceAdjustmentLog> getAdjustmentsByEvent(Long eventId);

    // controller-used
    PriceAdjustmentLog save(PriceAdjustmentLog log);
    List<PriceAdjustmentLog> findAll();
    Optional<PriceAdjustmentLog> findById(Long id);
    void deleteById(Long id);
}
