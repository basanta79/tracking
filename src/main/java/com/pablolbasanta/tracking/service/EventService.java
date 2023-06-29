package com.pablolbasanta.tracking.service;

import com.pablolbasanta.tracking.models.event.Event;
import com.pablolbasanta.tracking.models.event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    EventRepository repository;

    public Page<Event> getAllEventsBySessionPaginated(String sessionId, Integer pageNo, Integer pageSize) {

        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Event> pagedEvents = repository.findBySessionId(sessionId, paging);
        return pagedEvents;
    }
}
