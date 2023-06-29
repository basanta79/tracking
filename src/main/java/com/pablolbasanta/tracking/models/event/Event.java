package com.pablolbasanta.tracking.models.event;

import com.pablolbasanta.tracking.ApiModels.MachineEvent;
import jakarta.persistence.*;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "session_id", nullable = false)
    private String sessionId;
    @Column(name = "event_at", nullable = false)
    private Integer eventAt;
    @Column(name = "event_type", nullable = false)
    private String eventType;
    @Column(name = "numeric_event_value", nullable = false)
    private Float numericEventValue;

    public Event() {
    }

    public Event(String sessionId, MachineEvent event) {
        this.sessionId = sessionId;
        this.eventType = event.eventType();
        this.numericEventValue = event.numericEventValue();
        this.eventAt = event.eventAt();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Float getNumericEventValue() {
        return numericEventValue;
    }

    public void setNumericEventValue(Float numericEventValue) {
        this.numericEventValue = numericEventValue;
    }
}
