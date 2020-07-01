package rs.bane.alati.server.service;

import java.util.List;

import rs.bane.alati.server.model.Location;

public interface LocationService {

	Location findOne(Long id);

	List<Location> findAll();

	Location save(Location location);

	Location delete(Long id);

}
