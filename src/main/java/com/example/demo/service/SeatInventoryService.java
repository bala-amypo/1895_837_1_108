package com.example.demo.service;

import com.example.demo.model.SeatInventoryRecord;
import java.util.List;

public interface SeatInventoryService {
    SeatInventoryRecord create(SeatInventoryRecord record);
    List<SeatInventoryRecord> getAll();
    SeatInventoryRecord getByEventId(String eventCode);
}
