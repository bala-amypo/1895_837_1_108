
package com.example.demo.repository;

import com.example.demo.model.DynamicPriceRecord;
import java.util.*;

public interface DynamicPriceRecordRepository {
    List<DynamicPriceRecord> findByEventIdOrderByComputedAtDesc(Long id);
    Optional<DynamicPriceRecord> findFirstByEventIdOrderByComputedAtDesc(Long id);
    List<DynamicPriceRecord> findAll();
    DynamicPriceRecord save(DynamicPriceRecord r);
}
