package com.example.demo.service;

import com.example.demo.model.PriceAdjustmentLog;
import java.util.List;

public interface PriceAdjustmentLogService {

    PriceAdjustmentLog logAdjustment(PriceAdjustmentLog log);

    List<PriceAdjustmentLog> getAdjustments(Long eventId);

    List<PriceAdjustmentLog> getAll();
}
