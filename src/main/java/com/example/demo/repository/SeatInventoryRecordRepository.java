
package com.example.demo.repository;

import com.example.demo.model.SeatInventoryRecord;
import java.util.*;

public interface SeatInventoryRecordRepository {
    Optional<SeatInventoryRecord> findByEventId(Long eventId);
    List<SeatInventoryRecord> findAll();
    SeatInventoryRecord save(SeatInventoryRecord s);
}
