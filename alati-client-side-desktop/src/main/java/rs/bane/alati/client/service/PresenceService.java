package rs.bane.alati.client.service;

import java.util.Date;

import rs.bane.alati.client.model.Presence;

public interface PresenceService {

	Presence findByDateAndWorkerId(Date date, Long id);
	
	Presence findOne(Long id);
	
	Presence save(Presence presence);
	
	Presence delete(Long id);
	
}
