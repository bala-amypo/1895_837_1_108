
package com.example.demo.repository;

import com.example.demo.model.PriceAdjustmentLog;
import java.util.*;

public interface PriceAdjustmentLogRepository {
    List<PriceAdjustmentLog> findByEventId(Long id);
    PriceAdjustmentLog save(PriceAdjustmentLog l);
}
