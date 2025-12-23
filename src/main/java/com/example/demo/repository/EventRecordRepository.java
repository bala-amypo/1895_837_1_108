
package com.example.demo.repository;

import com.example.demo.model.EventRecord;
import java.util.*;

public interface EventRecordRepository {
    boolean existsByEventCode(String code);
    Optional<EventRecord> findById(Long id);
    Optional<EventRecord> findByEventCode(String code);
    List<EventRecord> findAll();
    EventRecord save(EventRecord e);
}
