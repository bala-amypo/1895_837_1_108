package com.example.demo.repository;

import com.example.demo.model.EventRecord;
import java.util.*;

public interface EventRecordRepository {

    boolean existsByEventCode(String eventCode);

    Optional<EventRecord> findById(Long id);

    Optional<EventRecord> findByEventCode(String eventCode);

    List<EventRecord> findAll();

    EventRecord save(EventRecord event);
}
