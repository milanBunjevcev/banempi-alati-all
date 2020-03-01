package rs.bane.alati.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.bane.alati.server.model.Location;
import rs.bane.alati.server.repository.LocationRepository;
import rs.bane.alati.server.service.LocationService;

@Service
public class JpaLocationService implements LocationService {

	@Autowired
	private LocationRepository locationRepository;

	@Override
	public Location findOne(Long id) {
		return locationRepository.findOne(id);
	}

	@Override
	public List<Location> findAll() {
		return locationRepository.findAll();
	}

	@Override
	public Location save(Location location) {
		return locationRepository.save(location);
	}

	@Override
	public Location delete(Long id) {
		Location toDelete = locationRepository.findOne(id);
		if (toDelete != null) {
			locationRepository.delete(id);
		}
		return toDelete;
	}

}
