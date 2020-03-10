package rs.bane.alati.server.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.bane.alati.server.model.Worker;
import rs.bane.alati.server.service.WorkerService;
import rs.bane.alati.server.support.dto.WorkerDTOToWorker;
import rs.bane.alati.server.support.dto.WorkerToWorkerDTO;
import rs.bane.alati.server.web.dto.WorkerDTO;

@RestController
@RequestMapping(value = "/api/workers")
public class ApiWorkerController {

	@Autowired
	private WorkerService workerService;

	@Autowired
	private WorkerToWorkerDTO toDTO;

	@Autowired
	private WorkerDTOToWorker toWorker;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<WorkerDTO>> getWorkers(
			@RequestParam(value = "nameOrLastName", required = false) String nameOrLastName,
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
			@RequestParam(value = "rowsPerPage", defaultValue = "20") int rowsPerPage) {

		Page<Worker> workerPage = null;

		if (nameOrLastName != null) {
			workerPage = workerService.findByNameLikeAndLastNameLike(nameOrLastName, pageNum, rowsPerPage);
		} else {
			workerPage = workerService.findAll(pageNum, rowsPerPage);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(workerPage.getTotalPages()));

		return new ResponseEntity<List<WorkerDTO>>(toDTO.convert(workerPage.getContent()), headers, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/all")
	ResponseEntity<List<WorkerDTO>> getWorkersAll() {
		List<Worker> workers = workerService.findAll();

		return new ResponseEntity<List<WorkerDTO>>(toDTO.convert(workers), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	ResponseEntity<WorkerDTO> getWorkers(@PathVariable Long id) {

		Worker worker = workerService.findOne(id);
		if (worker == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<WorkerDTO>(toDTO.convert(worker), HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	ResponseEntity<WorkerDTO> add(@RequestBody WorkerDTO newWorker) {

		Worker savedWorker = workerService.save(toWorker.convert(newWorker));

		return new ResponseEntity<WorkerDTO>(toDTO.convert(savedWorker), HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	ResponseEntity<WorkerDTO> delete(@PathVariable Long id) {

		Worker deleted = workerService.delete(id);
		if (deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<WorkerDTO>(toDTO.convert(deleted), HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	ResponseEntity<WorkerDTO> edit(@PathVariable Long id, @RequestBody WorkerDTO worker) {

		if (!id.equals(worker.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Worker edited = workerService.save(toWorker.convert(worker));
		return new ResponseEntity<WorkerDTO>(toDTO.convert(edited), HttpStatus.OK);

	}

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
