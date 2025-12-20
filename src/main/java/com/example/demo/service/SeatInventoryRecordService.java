package com.example.demo.service;

import com.example.demo.model.SeatInventoryRecord;
import java.util.List;
import java.util.Optional;

public interface SeatInventoryService {

    SeatInventoryRecord createInventory(SeatInventoryRecord inv);

    SeatInventoryRecord updateRemainingSeats(Long eventId, Integer remaining);

    Optional<SeatInventoryRecord> getInventoryByEvent(Long eventId);

    List<SeatInventoryRecord> getAllInventories();
}
