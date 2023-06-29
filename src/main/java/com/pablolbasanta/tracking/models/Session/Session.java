package com.pablolbasanta.tracking.models.Session;

import com.pablolbasanta.tracking.ApiModels.MachineSession;
import com.pablolbasanta.tracking.models.event.Event;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "session_id", nullable = false)
    private String sessionId;
    @Column(name = "machine_id", nullable = false)
    private String machineId;
    @Column(name = "start_at", nullable = false)
    private Integer startAt;
    private Boolean active;

    public Session() {
    }

    public Session(MachineSession session) {
        this.setActive(Boolean.TRUE);
        this.setStart_at(session.timestamp());
        this.setSession_id(session.sessionId());
        this.setMachine_id(session.machineId());
    }

    public void closeSession() {
        this.setActive(Boolean.FALSE);
    }

    public Integer getId() {
        return id;
    }

    public String getSession_id() {
        return sessionId;
    }

    public String getMachine_id() {
        return machineId;
    }

    public Integer getStart_at() {
        return startAt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSession_id(String session_id) {
        this.sessionId = session_id;
    }

    public void setMachine_id(String machine_id) {
        this.machineId = machine_id;
    }

    public void setStart_at(Integer start_at) {
        this.startAt = start_at;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}
