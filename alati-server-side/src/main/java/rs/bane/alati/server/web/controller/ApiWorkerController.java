package rs.bane.alati.server.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.bane.alati.server.model.radnik.Worker;
import rs.bane.alati.server.service.WorkerService;
import rs.bane.alati.server.support.WorkerToWorkerDTO;
import rs.bane.alati.server.web.dto.WorkerDTO;

@RestController
@RequestMapping(value = "/api/workers")
public class ApiWorkerController {

	@Autowired
	private WorkerService workerService;

	@Autowired
	private WorkerToWorkerDTO toDTO;

//	@RequestMapping(method = RequestMethod.GET)
//	ResponseEntity<List<WorkerDTO>> getWorkers() {
//		List<Worker> workers = workerService.findAll();
//		return new ResponseEntity<List<WorkerDTO>>(toDTO.convert(workers), HttpStatus.OK);
//	}

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<WorkerDTO>> getWorkers(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "lastname", required = false) String lastName) {
		List<Worker> workers = new ArrayList<Worker>();
		if (name != null && lastName != null) {
			workers = workerService.findByNameAndLastName(name, lastName);
		} else if (name != null) {
			workers = workerService.findByName(name);
		} else if (lastName != null) {
			workers = workerService.findByLastName(lastName);
		} else {
			workers = workerService.findAll();
		}
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

	@RequestMapping(method = RequestMethod.GET, value = "*")
	ResponseEntity<List<WorkerDTO>> fallback() {
		return new ResponseEntity<List<WorkerDTO>>(HttpStatus.NOT_FOUND);
	}

}
