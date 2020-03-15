package rs.bane.alati.server.support.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import rs.bane.alati.server.model.Presence;
import rs.bane.alati.server.web.dto.PresenceDTO;

@Component
public class PresenceToPresenceDTO implements Converter<Presence, PresenceDTO> {

	@Override
	public PresenceDTO convert(Presence source) {
		if (source == null) {
			return null;
		}

		PresenceDTO dto = new PresenceDTO();
		dto.setId(source.getId());
		dto.setDate(source.getDate());
		dto.setHours(source.getHours());
		dto.setWorkerId(source.getWorker().getId());

		return dto;
	}

	public List<PresenceDTO> convert(List<Presence> sourceList) {
		List<PresenceDTO> dtoList = new ArrayList<PresenceDTO>();
		for (Presence p : sourceList) {
			dtoList.add(convert(p));
		}
		return dtoList;
	}

}
