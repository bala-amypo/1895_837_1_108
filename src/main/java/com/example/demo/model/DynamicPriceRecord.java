package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dynamic_price_record")
public class DynamicPriceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long eventId;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private LocalDateTime calculatedAt;

    // ===== Constructors =====
    public DynamicPriceRecord() {}

    public DynamicPriceRecord(Long eventId, Double price, LocalDateTime calculatedAt) {
        this.eventId = eventId;
        this.price = price;
        this.calculatedAt = calculatedAt;
    }

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Double getPrice() {
        return price;
    }

    // 🔴 REQUIRED BY SERVICE IMPL
    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getCalculatedAt() {
        return calculatedAt;
    }

    // 🔴 REQUIRED BY SERVICE IMPL
    public void setCalculatedAt(LocalDateTime calculatedAt) {
        this.calculatedAt = calculatedAt;
    }
}
