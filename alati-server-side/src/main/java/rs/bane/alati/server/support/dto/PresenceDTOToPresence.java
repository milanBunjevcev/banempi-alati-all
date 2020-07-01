package rs.bane.alati.server.support.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import rs.bane.alati.server.model.Presence;
import rs.bane.alati.server.model.Worker;
import rs.bane.alati.server.service.PresenceService;
import rs.bane.alati.server.service.WorkerService;
import rs.bane.alati.server.web.dto.PresenceDTO;

@Component
public class PresenceDTOToPresence implements Converter<PresenceDTO, Presence> {

	@Autowired
	private WorkerService workerService;

	@Autowired
	private PresenceService presenceService;

	@Override
	public Presence convert(PresenceDTO dto) {
		Worker worker = workerService.findOne(dto.getWorkerId());

		if (worker != null) {
			Presence presence = null;

			if (dto.getId() != null) {
				presence = presenceService.findOne(dto.getId());
			} else {
				presence = new Presence();
			}

			presence.setDate(dto.getDate());
			presence.setHours(dto.getHours());
			presence.setWorker(worker);

			return presence;
		} else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}

	public List<Presence> convert(List<PresenceDTO> dtoList) {
		List<Presence> presenceList = new ArrayList<>();

		for (PresenceDTO dto : dtoList) {
			presenceList.add(convert(dto));
		}

		return presenceList;
	}

}
