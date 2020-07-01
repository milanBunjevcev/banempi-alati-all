package rs.bane.alati.server.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import rs.bane.alati.server.model.Presence;

public interface PresenceRepository extends JpaRepository<Presence, Long> {

	Presence findByDateAndWorkerId(@Param("date") Date date, @Param("id") Long id);

}