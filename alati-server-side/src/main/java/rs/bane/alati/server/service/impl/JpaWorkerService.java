package rs.bane.alati.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.bane.alati.server.model.Worker;
import rs.bane.alati.server.repository.WorkerRepository;
import rs.bane.alati.server.service.WorkerService;

@Service
public class JpaWorkerService implements WorkerService {

	@Autowired
	WorkerRepository workerRepository;

	@Override
	public Worker findOne(Long id) {
		return workerRepository.findOne(id);
	}

	@Override
	public List<Worker> findAll() {
		return workerRepository.findAll();
	}

	@Override
	public Worker save(Worker worker) {
		return workerRepository.save(worker);
	}

	@Override
	public List<Worker> save(List<Worker> workers) {
		return workerRepository.save(workers);
	}

	@Override
	public Worker delete(Long id) {
		Worker toDelete = workerRepository.findOne(id);
		if (toDelete != null) {
			workerRepository.delete(id);
		}
		return toDelete;
	}

	@Override
	public void delete(List<Long> ids) {
		for (Long id : ids) {
			workerRepository.delete(id);
		}
	}

	@Override
	public List<Worker> findByName(String name) {
		return workerRepository.findByName(name);
	}

	@Override
	public List<Worker> findByLastName(String lastName) {
		return workerRepository.findByLastName(lastName);
	}

	@Override
	public List<Worker> findByNameAndLastName(String name, String lastName) {
		return workerRepository.findByNameAndLastName(name, lastName);
	}

}
