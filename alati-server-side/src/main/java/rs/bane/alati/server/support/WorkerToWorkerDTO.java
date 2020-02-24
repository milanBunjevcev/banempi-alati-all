package rs.bane.alati.server.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import rs.bane.alati.server.model.Worker;
import rs.bane.alati.server.web.dto.WorkerDTO;

@Component
public class WorkerToWorkerDTO implements Converter<Worker, WorkerDTO> {

	@Override
	public WorkerDTO convert(Worker source) {
		if (source == null) {
			return null;
		}

		WorkerDTO dto = new WorkerDTO();

		dto.setId(source.getId());
		dto.setName(source.getName());
		dto.setLastName(source.getLastName());
		dto.setContractType(source.getContractType());

		return dto;
	}

	public List<WorkerDTO> convert(List<Worker> sourceList) {
		List<WorkerDTO> dtoList = new ArrayList<>();

		for (Worker curr : sourceList) {
			dtoList.add(convert(curr));
		}

		return dtoList;
	}

}
