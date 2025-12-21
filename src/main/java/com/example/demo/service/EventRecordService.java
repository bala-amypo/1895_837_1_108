package com.example.demo.service;

import com.example.demo.model.EventRecord;
import java.util.List;

public interface EventRecordService {

    EventRecord createEvent(EventRecord event);

    EventRecord getEventById(Long id);

    List<EventRecord> getAllEvents();

    EventRecord updateEventStatus(Long id, boolean active);

    EventRecord getEventByCode(String eventCode);
}
