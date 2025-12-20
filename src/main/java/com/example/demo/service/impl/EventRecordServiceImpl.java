package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.EventRecord;
import com.example.demo.repository.EventRecordRepository;
import com.example.demo.service.EventRecordService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventRecordServiceImpl implements EventRecordService {

    private final EventRecordRepository eventRepo;

    public EventRecordServiceImpl(EventRecordRepository eventRecordRepository) {
        this.eventRepo = eventRecordRepository;
    }

    @Override
    public EventRecord createEvent(EventRecord event) {

        if (eventRepo.existsByEventCode(event.getEventCode())) {
            throw new BadRequestException("Event code already exists");
        }

        if (event.getBasePrice() == null || event.getBasePrice() <= 0) {
            throw new BadRequestException("Base price must be > 0");
        }

        return eventRepo.save(event);
    }

    @Override
    public EventRecord getEventById(Long id) {
        return eventRepo.findById(id).orElseThrow(
                () -> new BadRequestException("Event not found")
        );
    }

    @Override
    public Optional<EventRecord> getEventByCode(String code) {
        return eventRepo.findByEventCode(code);
    }

    @Override
    public List<EventRecord> getAllEvents() {
        return eventRepo.findAll();
    }

    @Override
    public EventRecord updateEventStatus(Long id, boolean active) {
        EventRecord e = getEventById(id);
        e.setActive(active);
        return eventRepo.save(e);
    }
}
