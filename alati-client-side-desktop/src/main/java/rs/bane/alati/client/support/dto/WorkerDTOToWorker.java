package rs.bane.alati.client.support.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import rs.bane.alati.client.model.Worker;
import rs.bane.alati.client.service.WorkerService;
import rs.bane.alati.client.web.dto.WorkerDTO;

@Component
public class WorkerDTOToWorker implements Converter<WorkerDTO, Worker> {

	@Autowired
	private WorkerService workerService;

	@Override
	public Worker convert(WorkerDTO dto) {
		if (dto == null) {
			return null;
		}

		Worker worker = null;

		if (dto.getId() != null) {
			worker = workerService.findOne(dto.getId());
			worker.setActive(dto.isActive());
		} else {
			worker = new Worker();
		}

		worker.setName(dto.getName());
		worker.setLastName(dto.getLastName());
		worker.setContractType(dto.getContractType());

		return worker;
	}

	public List<Worker> convert(List<WorkerDTO> dtoList) {
		List<Worker> workerList = new ArrayList<>();

		for (WorkerDTO dto : dtoList) {
			workerList.add(convert(dto));
		}

		return workerList;
	}

}