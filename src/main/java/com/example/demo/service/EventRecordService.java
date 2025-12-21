package com.example.demo.service;

import com.example.demo.model.EventRecord;
import java.util.List;

public interface EventRecordService {
    EventRecord save(EventRecord event);
    List<EventRecord> findAll();
    EventRecord findById(Long id);
    void deleteById(Long id);
}
