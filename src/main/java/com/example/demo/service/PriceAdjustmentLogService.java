package com.example.demo.service;

import com.example.demo.model.PriceAdjustmentLog;
import java.util.List;

public interface PriceAdjustmentLogService {
    List<PriceAdjustmentLog> getLogs(String eventCode);
    List<PriceAdjustmentLog> getAll();
}
