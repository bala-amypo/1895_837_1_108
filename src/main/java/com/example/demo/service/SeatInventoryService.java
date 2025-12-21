package com.example.demo.service;

import com.example.demo.model.SeatInventoryRecord;
import java.util.List;

public interface SeatInventoryService {
    SeatInventoryRecord save(SeatInventoryRecord inventory);
    List<SeatInventoryRecord> findAll();
    SeatInventoryRecord findById(Long id);
    void deleteById(Long id);
}
