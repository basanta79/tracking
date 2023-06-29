package com.pablolbasanta.tracking.models.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Integer> {
    Page<Event> findBySessionId(String sessionId, Pageable pageable);

}
