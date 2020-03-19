package rs.bane.alati.server.support.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import rs.bane.alati.server.model.Worker;
import rs.bane.alati.server.web.dto.WorkerDTO;

@Component
public class WorkerDTOToWorker implements Converter<WorkerDTO, Worker> {

	@Override
	public Worker convert(WorkerDTO dto) {
		if (dto == null) {
			return null;
		}

		Worker worker = new Worker(dto.getId(), dto.getName(), dto.getLastName(), dto.getContractType());
		worker.setActive(dto.isActive());

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