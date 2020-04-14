package rs.bane.alati.client.service;

import java.util.List;

import rs.bane.alati.client.model.Location;

public interface LocationService {

	Location findOne(Long id);

	List<Location> findAll();

	Location save(Location location);

	Location delete(Long id);

}
