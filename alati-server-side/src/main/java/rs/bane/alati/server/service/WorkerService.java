package rs.bane.alati.server.service;

import java.util.List;

import rs.bane.alati.server.model.worker.Worker;

public interface WorkerService {
	Worker findOne(Long id);

	List<Worker> findAll();

	Worker save(Worker worker);

	List<Worker> save(List<Worker> workers);

	Worker delete(Long id);

	void delete(List<Long> ids);

	List<Worker> findByName(String name);
	
	List<Worker> findByLastName(String lastName);

	List<Worker> findByNameAndLastName(String name, String lastName);
}
