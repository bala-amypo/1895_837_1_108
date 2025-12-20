package com.example.demo.service;

import com.example.demo.model.EventRecord;
import java.util.List;

public interface EventRecordService {
    EventRecord create(EventRecord event);
    List<EventRecord> getAll();
    EventRecord getOne(Long id);
}
