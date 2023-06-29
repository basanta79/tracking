package com.pablolbasanta.tracking.Controllers;

import com.pablolbasanta.tracking.Exceptions.RecordNotFoundException;
import com.pablolbasanta.tracking.Queue.EventsConsumer;
import com.pablolbasanta.tracking.models.Session.Session;
import com.pablolbasanta.tracking.models.event.Event;
import com.pablolbasanta.tracking.service.EventService;
import com.pablolbasanta.tracking.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EventController {
    @Autowired
    SessionService sessionService;
    @Autowired
    EventService eventService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EventsConsumer.class);

    @GetMapping("/machines/{machineId}/sessions/{sessionId}/events")
    public ResponseEntity<Page<Event>> machineSessionEvent(@PathVariable String machineId,
                                                           @PathVariable String sessionId,
                                                           @RequestParam(defaultValue = "0") Integer pageNo,
                                                           @RequestParam(defaultValue = "10") Integer pageSize) throws RecordNotFoundException {

        LOGGER.info(String.format("machineSessionEvent -> machine: %s, session: %s", machineId, sessionId));

        Session last_session = sessionService.getLastSessionByMachineAndSession(machineId, sessionId);

        Page<Event> pagedEvents = eventService.getAllEventsBySessionPaginated(last_session.getSession_id(), pageNo, pageSize);

        return new ResponseEntity<Page<Event>>(pagedEvents, new HttpHeaders(), HttpStatus.OK);

    }
}
