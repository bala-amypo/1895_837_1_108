package com.example.demo.repository;

import com.example.demo.model.SeatInventoryRecord;
import java.util.List;
import java.util.Optional;

public interface SeatInventoryRecordRepository {

    Optional<SeatInventoryRecord> findByEventId(Long eventId);

    List<SeatInventoryRecord> findAll();

    SeatInventoryRecord save(SeatInventoryRecord inventory);
}
