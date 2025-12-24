package com.example.demo.repository;

import com.example.demo.model.*;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRecordRepository extends JpaRepository<EventRecord, Long> {
    boolean existsByEventCode(String code);
    Optional<EventRecord> findByEventCode(String code);
}
