package com.example.demo.repository;

import com.example.demo.model.DynamicPriceRecord;
import java.util.List;
import java.util.Optional;

public interface DynamicPriceRecordRepository {

    List<DynamicPriceRecord> findByEventIdOrderByComputedAtDesc(Long eventId);

    Optional<DynamicPriceRecord> findFirstByEventIdOrderByComputedAtDesc(Long eventId);

    List<DynamicPriceRecord> findAll();

    DynamicPriceRecord save(DynamicPriceRecord record);
}
