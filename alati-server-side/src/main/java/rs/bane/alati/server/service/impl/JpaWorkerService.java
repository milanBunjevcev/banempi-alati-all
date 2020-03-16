package rs.bane.alati.server.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import rs.bane.alati.server.model.Worker;
import rs.bane.alati.server.repository.WorkerRepository;
import rs.bane.alati.server.service.WorkerService;

@Service
@Transactional
public class JpaWorkerService implements WorkerService {

	@Autowired
	WorkerRepository workerRepository;

	@Override
	public Worker findOne(Long id) {
		return workerRepository.findOne(id);
	}

	private Direction setDirection(int direction) {
		Direction sortDirection;
		if (direction <= 0) {
			sortDirection = Sort.Direction.ASC;
		} else {
			sortDirection = Sort.Direction.DESC;
		}
		return sortDirection;
	}

	@Override
	public Page<Worker> findAll(int pageNum, int rowsPerPage) {
		return workerRepository.findAll(new PageRequest(pageNum, rowsPerPage));
	}

	@Override
	public Page<Worker> findAllSorted(int pageNum, int rowsPerPage, int direction, String sortBy) {
		return workerRepository.findAll(new PageRequest(pageNum, rowsPerPage, setDirection(direction), sortBy));
	}

	@Override
	public List<Worker> findAll() {
		return workerRepository.findAll();
	}

	@Override
	public List<Worker> findAllSorted(int direction, String sortBy) {
		return workerRepository.findAll(new Sort(setDirection(direction), sortBy));
	}

	@Override
	public Worker save(Worker worker) {
		return workerRepository.save(worker);
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
	public Page<Worker> findByNameLikeAndLastNameLike(String nameOrLastName, int pageNum, int rowsPerPage,
			int direction, String sortBy) {
		String[] tokens = nameOrLastName.split(" ");
		String name = null;
		String lastName = null;
		if (tokens.length == 1) {
			name = "%" + tokens[0] + "%";
		} else {
			name = "%" + tokens[0] + "%";
			lastName = "%" + tokens[1] + "%";
		}
		return workerRepository.findByNameLikeAndLastNameLikeByOrderByLastName(name, lastName,
				new PageRequest(pageNum, rowsPerPage, setDirection(direction), sortBy));
	}

}
