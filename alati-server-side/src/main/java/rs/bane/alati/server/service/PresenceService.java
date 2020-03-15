package rs.bane.alati.server.service;

import java.util.Date;

import rs.bane.alati.server.model.Presence;

public interface PresenceService {

	Presence findByDateAndWorkerId(Date date, Long id);
	
	Presence findOne(Long id);
	
	Presence save(Presence presence);
	
	Presence delete(Long id);
	
}
