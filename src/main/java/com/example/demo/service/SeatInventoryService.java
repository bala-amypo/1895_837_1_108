package com.example.demo.service;

import com.example.demo.model.SeatInventoryRecord;
import java.util.List;
import java.util.Optional;

public interface SeatInventoryService {

    // test-used
    SeatInventoryRecord createInventory(SeatInventoryRecord inventory);
    SeatInventoryRecord getInventoryByEvent(Long eventId);
    List<SeatInventoryRecord> getAllInventories();

    // controller-used
    SeatInventoryRecord save(SeatInventoryRecord inventory);
    List<SeatInventoryRecord> findAll();
    Optional<SeatInventoryRecord> findById(Long id);
    void deleteById(Long id);
}
