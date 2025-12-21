package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.EventRecord;

public interface EventRecordRepository extends JpaRepository<EventRecord, Long> {
}
