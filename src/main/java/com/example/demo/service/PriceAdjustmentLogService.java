package com.example.demo.service;

import com.example.demo.model.PriceAdjustmentLog;
import java.util.List;

public interface PriceAdjustmentLogService {

    PriceAdjustmentLog save(PriceAdjustmentLog log);

    List<PriceAdjustmentLog> findAll();

    PriceAdjustmentLog findById(Long id);

    void deleteById(Long id);
}
