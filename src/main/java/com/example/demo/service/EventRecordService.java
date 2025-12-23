package com.example.demo.service;

import com.example.demo.model.EventRecord;

import java.util.List;
import java.util.Optional;

public interface EventRecordService {

    // ===== Used by TESTS =====
    EventRecord createEvent(EventRecord event);
    EventRecord getEventById(Long id);
    Optional<EventRecord> getEventByCode(String eventCode);
    List<EventRecord> getAllEvents();
    EventRecord updateEventStatus(Long id, boolean active);

    // ===== Used by CONTROLLERS =====
    EventRecord save(EventRecord event);
    List<EventRecord> findAll();
    EventRecord findById(Long id);
    void deleteById(Long id);
}
