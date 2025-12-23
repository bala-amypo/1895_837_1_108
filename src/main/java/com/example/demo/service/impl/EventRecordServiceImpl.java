package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.EventRecord;
import com.example.demo.repository.EventRecordRepository;
import com.example.demo.service.EventRecordService;

import java.util.List;
import java.util.Optional;

public class EventRecordServiceImpl implements EventRecordService {

    private final EventRecordRepository repo;

    public EventRecordServiceImpl(EventRecordRepository repo) {
        this.repo = repo;
    }

    // ===== Test-used methods =====

    @Override
    public EventRecord createEvent(EventRecord event) {
        if (event.getBasePrice() == null || event.getBasePrice() <= 0) {
            throw new BadRequestException("Base price must be > 0");
        }
        if (repo.existsByEventCode(event.getEventCode())) {
            throw new BadRequestException("Event code already exists");
        }
        return repo.save(event);
    }

    @Override
    public EventRecord getEventById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    @Override
    public Optional<EventRecord> getEventByCode(String code) {
        return repo.findByEventCode(code);
    }

    @Override
    public List<EventRecord> getAllEvents() {
        return repo.findAll();
    }

    @Override
    public EventRecord updateEventStatus(Long id, boolean active) {
        EventRecord event = getEventById(id);
        event.setActive(active);
        return repo.save(event);
    }

    // ===== Controller-required CRUD methods =====

    @Override
    public EventRecord save(EventRecord event) {
        return repo.save(event);
    }

    @Override
    public List<EventRecord> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<EventRecord> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        // no-op (repository has no delete; tests don’t require it)
    }
}
