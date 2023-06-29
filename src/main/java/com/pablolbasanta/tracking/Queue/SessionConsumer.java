package com.pablolbasanta.tracking.Queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablolbasanta.tracking.ApiModels.MachineSession;
import com.pablolbasanta.tracking.models.Session.Session;
import com.pablolbasanta.tracking.models.Session.SessionRepository;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SessionConsumer {
    @Autowired
    private SessionRepository sessionRepository;
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.session_queue.name}"})
    public void session_consumer(String message){
        LOGGER.info(String.format("Received session -> %s", message));

        MachineSession api_session = parse_incoming_message(message);
        close_older_sessions(api_session);
        create_and_save_session(api_session);
        Session s = sessionRepository.findLastActiveSessionByMachineId(api_session.machineId());

        LOGGER.info(String.format("Session saved ..."));

    }

    private MachineSession parse_incoming_message(String message){

        try {
            return new ObjectMapper().readValue(message, MachineSession.class);
        }catch (JsonProcessingException error) {
            LOGGER.info(String.format("Error parsing message from queue"));
            return null;
        }
    }

    private void create_and_save_session(MachineSession api_session) {
        Session session = new Session(api_session);
        sessionRepository.save(session);
    }

    private void close_older_sessions(MachineSession api_session){
        List<Session> last_sessions = sessionRepository.findByMachineIdAndActive(api_session.machineId(), Boolean.TRUE);
        for (Session session : last_sessions) {
            session.closeSession();
            sessionRepository.save(session);
        }
    }
}
