package com.pablolbasanta.tracking.ApiModels;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

@JsonSerialize
public record MachineSession(String sessionId, String machineId, Integer timestamp) {
}
