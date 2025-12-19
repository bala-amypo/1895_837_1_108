package com.example.aiml.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class SeatInventoryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventId;
    private Integer totalSeats;
    private Integer remainingSeats;
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    public SeatInventoryRecord() {
    }

    public SeatInventoryRecord(Long id, Long eventId, Integer totalSeats,
                               Integer remainingSeats, LocalDateTime updatedAt) {

        this.id = id;
        this.eventId = eventId;
        this.totalSeats = totalSeats;
        this.remainingSeats = remainingSeats;
        this.updatedAt = updatedAt;
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

    public Integer getTotalSeats() {
        return totalSeats;
    }
    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Integer getRemainingSeats() {
        return remainingSeats;
    }
    public void setRemainingSeats(Integer remainingSeats) {
        this.remainingSeats = remainingSeats;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
