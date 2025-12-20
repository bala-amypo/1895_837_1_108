package com.example.demo.service;

import com.example.demo.model.SeatInventoryRecord;

public interface SeatInventoryService {

    SeatInventoryRecord create(SeatInventoryRecord record);

    SeatInventoryRecord getByEventId(Long eventId);
}
