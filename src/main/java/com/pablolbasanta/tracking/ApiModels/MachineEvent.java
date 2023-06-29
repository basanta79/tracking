package com.pablolbasanta.tracking.ApiModels;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

@JsonSerialize
public record MachineEvent(String eventType, float numericEventValue, Integer eventAt) {
}
