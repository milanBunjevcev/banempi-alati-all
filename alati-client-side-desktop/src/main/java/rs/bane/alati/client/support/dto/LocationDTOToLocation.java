package rs.bane.alati.client.support.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import rs.bane.alati.client.model.Location;
import rs.bane.alati.client.service.LocationService;
import rs.bane.alati.client.web.dto.LocationDTO;

@Component
public class LocationDTOToLocation implements Converter<LocationDTO, Location> {

	@Autowired
	private LocationService locationService;

	@Override
	public Location convert(LocationDTO dto) {

		if (dto == null) {
			return null;
		}

		Location location = null;
		if (dto.getId() != null) {
			location = locationService.findOne(dto.getId());
		} else {
			location = new Location();
		}

		location.setId(dto.getId());
		location.setName(dto.getName());

		return location;

	}

}
