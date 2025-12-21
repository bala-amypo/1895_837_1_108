package com.example.demo.service;

import com.example.demo.model.SeatInventoryRecord;

import java.util.List;

public interface SeatInventoryService {
    SeatInventoryRecord save(SeatInventoryRecord record);
    SeatInventoryRecord getByEventId(Long eventId);
    List<SeatInventoryRecord> getAll();
}
