package com.pablolbasanta.tracking.Queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablolbasanta.tracking.ApiModels.MachineEvent;
import com.pablolbasanta.tracking.ApiModels.MachineEvents;
import com.pablolbasanta.tracking.models.event.Event;
import com.pablolbasanta.tracking.models.event.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EventsConsumer {
    @Autowired
    private EventRepository eventRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(EventsConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.events_queue.name}"})
    public void eventsConsumer(String message) throws JsonProcessingException {
        LOGGER.info(String.format("Received event -> %s", message));

        MachineEvents api_events = parseIncomingMessage(message);
        createAndSaveEvents(api_events);

        LOGGER.info(String.format("Event saved ..."));

    }

    private MachineEvents parseIncomingMessage(String message){

        try {
            return new ObjectMapper().readValue(message, MachineEvents.class);
        }catch (JsonProcessingException error) {
            LOGGER.info(String.format("Error parsing message from queue"));
            return null;
        }
    }

    private void createAndSaveEvents(MachineEvents apiEvents) {
        String sessionId = apiEvents.sessionId();
        for (MachineEvent apiEvent : apiEvents.events()) {
            Event event = new Event(sessionId, apiEvent);
            eventRepository.save(event);
        }
    }
}
