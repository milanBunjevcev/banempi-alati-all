package rs.bane.alati.client.support.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import rs.bane.alati.client.model.Location;
import rs.bane.alati.client.web.dto.LocationDTO;

@Component
public class LocationToLocationDTO implements Converter<Location, LocationDTO> {

	@Override
	public LocationDTO convert(Location source) {
		if (source == null) {
			return null;
		}

		LocationDTO dto = new LocationDTO();
		dto.setId(source.getId());
		dto.setName(source.getName());

		return dto;
	}

	public List<LocationDTO> convert(List<Location> locationList) {
		List<LocationDTO> dtoList = new ArrayList<LocationDTO>();
		for (Location l : locationList) {
			dtoList.add(convert(l));
		}
		return dtoList;
	}

}
