package rs.bane.alati.client.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.bane.alati.client.model.Presence;
import rs.bane.alati.client.repository.PresenceRepository;
import rs.bane.alati.client.service.PresenceService;

@Service
@Transactional
public class JpaPresenceService implements PresenceService {

	@Autowired
	private PresenceRepository presenceRepository;

	@Override
	public Presence findByDateAndWorkerId(Date date, Long id) {
		return presenceRepository.findByDateAndWorkerId(date, id);
	}

	@Override
	public Presence save(Presence presence) {
		return presenceRepository.save(presence);
	}

	@Override
	public Presence findOne(Long id) {
		return presenceRepository.findOne(id);
	}

	@Override
	public Presence delete(Long id) {
		Presence toDelete = presenceRepository.findOne(id);
		if (toDelete != null) {
			presenceRepository.delete(id);
		}
		return toDelete;
	}

}
