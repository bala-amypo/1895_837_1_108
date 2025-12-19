package com.example.aiml.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class EventRecord {
    private Long id;
    private String eventCode;
    private String eventName;
    private String venue;
    private LocalDate eventDate;
    private Double basePrice;
    private LocalDateTime createdAt;
    private Boolean active;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEventCode() {
        return eventCode;
    }
    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public String getVenue() {
        return venue;
    }
    public void setVenue(String venue) {
        this.venue = venue;
    }
    public LocalDate getEventDate() {
        return eventDate;
    }
    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }
    public Double getBasePrice() {
        return basePrice;
    }
    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }

    public EventRecord(Long id, String eventCode, String eventName, String venue, LocalDate eventDate, Double basePrice,
            LocalDateTime createdAt, Boolean active) {
        this.id = id;
        this.eventCode = eventCode;
        this.eventName = eventName;
        this.venue = venue;
        this.eventDate = eventDate;
        this.basePrice = basePrice;
        this.createdAt = createdAt;
        this.active = active;
    }
    public EventRecord() {
    }
    
    
}

