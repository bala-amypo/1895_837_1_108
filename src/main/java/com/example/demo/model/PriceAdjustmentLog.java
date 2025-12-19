package com.example.demo.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class PriceAdjustmentLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventId;
    private Double oldPrice;
    private Double newPrice;
    private String reason;
    private LocalDateTime changedAt;


    public PriceAdjustmentLog() {
    }


    public PriceAdjustmentLog(Long id, Long eventId, Double oldPrice,
                              Double newPrice, String reason,
                              LocalDateTime changedAt) {

        this.id = id;
        this.eventId = eventId;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.reason = reason;
        this.changedAt = changedAt;
    }


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


    public Double getOldPrice() {
        return oldPrice;
    }
    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }


    public Double getNewPrice() {
        return newPrice;
    }
    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }


    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }


    public LocalDateTime getChangedAt() {
        return changedAt;
    }
    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }

}
