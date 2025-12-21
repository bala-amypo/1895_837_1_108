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

    private double computedPrice;

    private LocalDateTime computedAt;

    private Double previousPrice;

    private String appliedRuleCodes;

    /* ------------------ JPA lifecycle ------------------ */

    @PrePersist
    public void prePersist() {
        this.computedAt = LocalDateTime.now();
    }

    /* ------------------ getters & setters ------------------ */

    public Long getId() {
        return id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    /* --- REQUIRED BY TESTS & SERVICE --- */

    public double getComputedPrice() {
        return computedPrice;
    }

    public void setComputedPrice(double computedPrice) {
        this.computedPrice = computedPrice;
    }

    /* compatibility with service impl */
    public void setPrice(double price) {
        this.computedPrice = price;
    }

    public LocalDateTime getComputedAt() {
        return computedAt;
    }

    public void setComputedAt(LocalDateTime computedAt) {
        this.computedAt = computedAt;
    }

    /* compatibility with service impl */
    public void setCalculatedAt(LocalDateTime time) {
        this.computedAt = time;
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
}
