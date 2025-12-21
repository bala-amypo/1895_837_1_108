package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.repository.SeatInventoryRecordRepository;
import com.example.demo.service.SeatInventoryService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SeatInventoryServiceImpl implements SeatInventoryService {

    private final SeatInventoryRecordRepository repository;

    public SeatInventoryServiceImpl(SeatInventoryRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public SeatInventoryRecord save(SeatInventoryRecord inventory) {
        if (inventory.getRemainingSeats() > inventory.getTotalSeats()) {
            throw new BadRequestException("Remaining seats cannot exceed total seats");
        }
        return repository.save(inventory);
    }

    @Override
    public List<SeatInventoryRecord> findAll() {
        return repository.findAll();
    }

    @Override
    public SeatInventoryRecord findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
