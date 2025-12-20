package com.example.demo.service.impl;

import com.example.demo.model.EventRecord;
import com.example.demo.repository.EventRecordRepository;
import com.example.demo.service.EventRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventRecordServiceImpl implements EventRecordService {

    private final EventRecordRepository repo;

    public EventRecordServiceImpl(EventRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public EventRecord create(EventRecord event) {
        return repo.save(event);
    }

    @Override
    public List<EventRecord> getAll() {
        return repo.findAll();
    }

    @Override
    public EventRecord getOne(Long id) {
        return repo.findById(id).orElse(null);
    }
}
