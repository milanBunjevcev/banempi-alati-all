package rs.bane.alati.server.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import rs.bane.alati.server.model.Lokacija;
import rs.bane.alati.server.web.dto.LokacijaDTO;

@Component
public class LokacijaToLokacijaDTO implements Converter<Lokacija, LokacijaDTO> {

	@Override
	public LokacijaDTO convert(Lokacija source) {
		if (source == null) {
			return null;
		}

		LokacijaDTO dto = new LokacijaDTO();
		dto.setId(source.getId());
		dto.setNazivLokacije(source.getNazivLokacije());

		return dto;
	}

	public List<LokacijaDTO> convert(List<Lokacija> lokacijaList) {

	}

}
