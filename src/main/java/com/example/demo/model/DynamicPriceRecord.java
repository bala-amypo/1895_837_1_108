package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dynamic_price_records")
public class DynamicPriceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double computedPrice;
    private LocalDateTime computedAt;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonBackReference
    private EventRecord event;

    @PrePersist
    public void setComputedAt() {
        computedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getComputedPrice() { return computedPrice; }
    public void setComputedPrice(Double computedPrice) { this.computedPrice = computedPrice; }

    public EventRecord getEvent() { return event; }
    public void setEvent(EventRecord event) { this.event = event; }
}
