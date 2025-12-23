package com.example.demo.service;

import com.example.demo.model.SeatInventoryRecord;

import java.util.List;

public interface SeatInventoryService {

    // ===== Used by TESTS =====
    SeatInventoryRecord createInventory(SeatInventoryRecord inventory);
    SeatInventoryRecord getInventoryByEvent(Long eventId);
    List<SeatInventoryRecord> getAllInventories();

    // ===== Used by CONTROLLERS =====
    SeatInventoryRecord save(SeatInventoryRecord inventory);
    List<SeatInventoryRecord> findAll();
    SeatInventoryRecord findById(Long id);
    void deleteById(Long id);
}
