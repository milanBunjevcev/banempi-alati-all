package rs.bane.alati.server.service;

import java.util.List;

import org.springframework.data.domain.Page;

import rs.bane.alati.server.model.Worker;

public interface WorkerService {
	Worker findOne(Long id);

	Page<Worker> findAll(int pageNum, int rowsPerPage);

	List<Worker> findAll();

	Worker save(Worker worker);

	Worker delete(Long id);

	void delete(List<Long> ids);

	Page<Worker> findByNameLikeAndLastNameLike(String nameOrLastName, int pageNum, int rowsPerPage);

}
