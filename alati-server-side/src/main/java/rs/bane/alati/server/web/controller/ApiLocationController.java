package rs.bane.alati.server.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.bane.alati.server.model.Location;
import rs.bane.alati.server.service.LocationService;
import rs.bane.alati.server.support.dto.LocationDTOToLocation;
import rs.bane.alati.server.support.dto.LocationToLocationDTO;
import rs.bane.alati.server.web.dto.LocationDTO;

@RestController
@RequestMapping(value = "/api/locations")
public class ApiLocationController {

	@Autowired
	private LocationService locationService;

	@Autowired
	private LocationToLocationDTO toDto;

	@Autowired
	private LocationDTOToLocation toLocation;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<LocationDTO> get(@PathVariable Long id) {
		Location l = locationService.findOne(id);
		if (l == null) {
			return new ResponseEntity<LocationDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<LocationDTO>(toDto.convert(l), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<LocationDTO>> get() {
		List<Location> locations = locationService.findAll();
		return new ResponseEntity<List<LocationDTO>>(toDto.convert(locations), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	ResponseEntity<LocationDTO> delete(@PathVariable Long id) {
		Location deleted = locationService.delete(id);
		if (deleted == null) {
			return new ResponseEntity<LocationDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<LocationDTO>(toDto.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	ResponseEntity<LocationDTO> add(@RequestBody LocationDTO locDto) {
		Location newLocation = locationService.save(toLocation.convert(locDto));
		return new ResponseEntity<LocationDTO>(toDto.convert(newLocation), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	ResponseEntity<LocationDTO> edit(@PathVariable Long id, @RequestBody LocationDTO locDto) {
		if (!id.equals(locDto.getId())) {
			return new ResponseEntity<LocationDTO>(HttpStatus.BAD_REQUEST);
		}
		Location edited = locationService.save(toLocation.convert(locDto));
		return new ResponseEntity<LocationDTO>(toDto.convert(edited), HttpStatus.OK);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
