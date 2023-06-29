package com.pablolbasanta.tracking.service;

import com.pablolbasanta.tracking.Exceptions.RecordNotFoundException;
import com.pablolbasanta.tracking.models.Session.Session;
import com.pablolbasanta.tracking.models.Session.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {
    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private SessionService sessionService;

    private Session session;

    @BeforeEach
    public void setup(){
        session = new Session();
    }

    @DisplayName("JUnit test for getLastSessionByMachineAndSession method")
    @Test
    public void givenSessionObject_whenFindSession_thenReturnSessionObject() throws RecordNotFoundException {
        // given - precondition or setup
        given(sessionRepository.findLastActiveSessionByMachineIdAndSessionId("machine_id", "session_id"))
                .willReturn(Optional.ofNullable(session));

        System.out.println(sessionService);

        // when -  action or the behaviour that we are going test
        Session session = sessionService.getLastSessionByMachineAndSession("machine_id", "session_id");

        System.out.println(session);

        // then - verify the output
        assertThat(session).isNotNull();
    }


}