package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DynamicPriceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventId;

    // REQUIRED by testcases
    private Double computedPrice;
    private Double previousPrice;
    private String appliedRuleCodes;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // ---------- Lifecycle hooks ----------
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // ---------- Getters & Setters ----------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Double getComputedPrice() {
        return computedPrice;
    }

    public void setComputedPrice(Double computedPrice) {
        this.computedPrice = computedPrice;
    }

    public Double getPreviousPrice() {
        return previousPrice;
    }

    public void setPreviousPrice(Double previousPrice) {
        this.previousPrice = previousPrice;
    }

    public String getAppliedRuleCodes() {
        return appliedRuleCodes;
    }

    public void setAppliedRuleCodes(String appliedRuleCodes) {
        this.appliedRuleCodes = appliedRuleCodes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
