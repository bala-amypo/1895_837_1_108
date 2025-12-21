package com.example.demo.service.impl;

import com.example.demo.model.SeatInventoryRecord;
import com.example.demo.repository.SeatInventoryRecordRepository;
import com.example.demo.repository.EventRecordRepository;
import com.example.demo.service.SeatInventoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatInventoryServiceImpl implements SeatInventoryService {

    private final SeatInventoryRecordRepository seatRepo;
    private final EventRecordRepository eventRepo;

    public SeatInventoryServiceImpl(
            SeatInventoryRecordRepository seatRepo,
            EventRecordRepository eventRepo
    ) {
        this.seatRepo = seatRepo;
        this.eventRepo = eventRepo;
    }

    @Override
    public SeatInventoryRecord create(SeatInventoryRecord record) {

        // validate event exists (portal testcase requirement)
        if (!eventRepo.existsById(record.getEventId())) {
            throw new RuntimeException("Invalid eventId");
        }

        return seatRepo.save(record);
    }

    @Override
    public List<SeatInventoryRecord> getAll() {
        return seatRepo.findAll();
    }

    @Override
    public List<SeatInventoryRecord> getByEvent(String eventCode) {

        // convert ID string to long
        Long eventId = Long.parseLong(eventCode);

        return seatRepo.findByEventId(eventId);
    }
}
