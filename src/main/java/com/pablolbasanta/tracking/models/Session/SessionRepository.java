package com.pablolbasanta.tracking.models.Session;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface SessionRepository extends CrudRepository<Session, Integer> {
    List<Session> findByMachineIdAndActive(String machine_id, Boolean active);

    @Query("select s from Session s where s.machineId = ?1 and active = TRUE order by id desc limit 1")
    Session findLastActiveSessionByMachineId(String machineId);

    @Query("select s from Session s where s.machineId = ?1 and s.sessionId = ?2 and active = TRUE order by id desc limit 1")
    Optional<Session> findLastActiveSessionByMachineIdAndSessionId(String machineId, String sessionId);
}
