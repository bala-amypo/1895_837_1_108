package com.example.demo.repository;

import com.example.demo.model.DynamicPriceRecord;
import java.util.*;

public interface DynamicPriceRecordRepository {

    Optional<DynamicPriceRecord> findFirstByEventIdOrderByComputedAtDesc(Long eventId);

    List<DynamicPriceRecord> findByEventIdOrderByComputedAtDesc(Long eventId);

    List<DynamicPriceRecord> findAll();

    DynamicPriceRecord save(DynamicPriceRecord record);
}
