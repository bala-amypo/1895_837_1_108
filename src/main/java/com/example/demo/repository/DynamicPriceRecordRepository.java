package com.example.demo.repository;

import com.example.demo.model.DynamicPriceRecord;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DynamicPriceRecordRepository extends JpaRepository<DynamicPriceRecord, Long> {
    Optional<DynamicPriceRecord> findFirstByEventIdOrderByComputedAtDesc(Long eventId);
    List<DynamicPriceRecord> findByEventIdOrderByComputedAtDesc(Long eventId);
}
