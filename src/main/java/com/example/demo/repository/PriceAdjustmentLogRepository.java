package com.example.demo.repository;

import com.example.demo.model.PriceAdjustmentLog;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceAdjustmentLogRepository extends JpaRepository<PriceAdjustmentLog, Long> {
    List<PriceAdjustmentLog> findByEventId(Long eventId);
}
