package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dynamic_price_records")
public class DynamicPriceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventId;

    private Double price;

    private LocalDateTime calculatedAt;

    // ---------- Constructors ----------
    public DynamicPriceRecord() {
    }

    public DynamicPriceRecord(Long eventId, Double price, LocalDateTime calculatedAt) {
        this.eventId = eventId;
        this.price = price;
        this.calculatedAt = calculatedAt;
    }

    // ---------- Getters ----------
    public Long getId() {
        return id;
    }

    public Long getEventId() {
        return eventId;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDateTime getCalculatedAt() {
        return calculatedAt;
    }

    // ---------- Setters ----------
    public void setId(Long id) {
        this.id = id;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCalculatedAt(LocalDateTime calculatedAt) {
        this.calculatedAt = calculatedAt;
    }
}
