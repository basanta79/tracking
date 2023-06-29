package com.pablolbasanta.tracking.Controllers;

import com.pablolbasanta.tracking.ApiModels.MachineEvent;
import com.pablolbasanta.tracking.ApiModels.MachineEvents;
import com.pablolbasanta.tracking.ApiModels.MachineSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SendEventsController {
    private final RabbitTemplate rabbitTemplate;

    public SendEventsController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/send/session")
    public MachineSession send_session(
            @RequestParam(value = "session_id") String session_id,
            @RequestParam(value = "machine_id") String machine_id,
            @RequestParam(value = "start_at") Integer start_at) {

        Jsonb jsonb = JsonbBuilder.create();
        MachineSession result = new MachineSession(session_id, machine_id, start_at);
        var serialized = jsonb.toJson(result);
        rabbitTemplate.convertAndSend("machine-event", "session", serialized);
        return result;
    }

    @PostMapping("/send/events")
    public MachineEvents send_events(
            @RequestParam(value = "session_id") String session_id,
            @RequestParam(value = "event_type") String event_type,
            @RequestParam(value = "numeric_event_value") float numeric_event_value,
            @RequestParam(value = "event_at") Integer event_at) {

        Jsonb jsonb = JsonbBuilder.create();
        MachineEvent event = new MachineEvent(event_type, numeric_event_value, event_at);
        List<MachineEvent> event_list = new ArrayList<MachineEvent>();
        event_list.add(event);
        event_list.add(event);
        event_list.add(event);
        MachineEvents result = new MachineEvents(session_id, event_list);
        var serialized = jsonb.toJson(result);
        rabbitTemplate.convertAndSend("machine-event", "events", serialized);
        return result;
    }

}
