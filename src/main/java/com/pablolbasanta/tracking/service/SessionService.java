package com.pablolbasanta.tracking.service;

import com.pablolbasanta.tracking.Exceptions.RecordNotFoundException;
import com.pablolbasanta.tracking.models.Session.Session;
import com.pablolbasanta.tracking.models.Session.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    SessionRepository repository;

    public Session getLastSessionByMachineAndSession(String machineId, String sessionId) throws RecordNotFoundException {
        Optional<Session> last_session = repository.findLastActiveSessionByMachineIdAndSessionId(machineId, sessionId);
        if (last_session.isEmpty()) {
            throw new RecordNotFoundException("No session found");
        }
        return last_session.get();
    }
}
