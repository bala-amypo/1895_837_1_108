package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.EventRecord;
import com.example.demo.repository.EventRecordRepository;
import com.example.demo.service.EventRecordService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventRecordServiceImpl implements EventRecordService {

    private final EventRecordRepository repository;

    public EventRecordServiceImpl(EventRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public EventRecord save(EventRecord event) {
        if (event.getBasePrice() == null || event.getBasePrice() <= 0) {
            throw new BadRequestException("Base price must be > 0");
        }
        return repository.save(event);
    }

    @Override
    public List<EventRecord> findAll() {
        return repository.findAll();
    }

    @Override
    public EventRecord findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
