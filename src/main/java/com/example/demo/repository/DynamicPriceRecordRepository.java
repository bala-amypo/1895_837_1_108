package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.DynamicPriceRecord;

public interface DynamicPriceRecordRepository extends JpaRepository<DynamicPriceRecord, Long> {
}
