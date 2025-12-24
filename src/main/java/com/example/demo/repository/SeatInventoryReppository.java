package com.example.demo.repository;

import com.example.demo.model.SeatInventoryRecord;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatInventoryRecordRepository extends JpaRepository<SeatInventoryRecord, Long> {
    Optional<SeatInventoryRecord> findByEventId(Long eventId);
}
