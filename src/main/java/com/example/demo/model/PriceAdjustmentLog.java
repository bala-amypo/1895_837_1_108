package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "price_adjustment_logs")
public class PriceAdjustmentLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double oldPrice;
    private Double newPrice;
    private String reason;
    private LocalDateTime changedAt;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonBackReference
    private EventRecord event;

    @PrePersist
    public void setChangedAt() {
        changedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getOldPrice() { return oldPrice; }
    public void setOldPrice(Double oldPrice) { this.oldPrice = oldPrice; }

    public Double getNewPrice() { return newPrice; }
    public void setNewPrice(Double newPrice) { this.newPrice = newPrice; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public EventRecord getEvent() { return event; }
    public void setEvent(EventRecord event) { this.event = event; }
}
