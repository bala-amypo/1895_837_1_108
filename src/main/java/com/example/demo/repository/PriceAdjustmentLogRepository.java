package com.example.demo.repository;

import com.example.demo.model.PriceAdjustmentLog;
import java.util.List;

public interface PriceAdjustmentLogRepository {

    List<PriceAdjustmentLog> findByEventId(Long eventId);

    PriceAdjustmentLog save(PriceAdjustmentLog log);
}
