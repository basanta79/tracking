package com.pablolbasanta.tracking.ApiModels;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize
public record MachineEvents(String sessionId, List<MachineEvent> events) {
}
