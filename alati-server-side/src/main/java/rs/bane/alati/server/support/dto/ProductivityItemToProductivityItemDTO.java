package rs.bane.alati.server.support.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import rs.bane.alati.server.model.ProductivityItem;
import rs.bane.alati.server.model.Worker;
import rs.bane.alati.server.web.dto.ProductivityItemDTO;

@Component
public class ProductivityItemToProductivityItemDTO implements Converter<ProductivityItem, ProductivityItemDTO> {

	@Override
	public ProductivityItemDTO convert(ProductivityItem source) {
		if (source == null) {
			return null;
		}

		ProductivityItemDTO dto = new ProductivityItemDTO();

		dto.setId(source.getId());
		dto.setWorkOrderNumber(source.getWorkOrderNumber());
		dto.setTechOperationNumber(source.getTechOperationNumber());
		dto.setOperationName(source.getOperationName());
		dto.setTechOutturn(source.getTechOutturn());
		dto.setProductName(source.getProductName());
		dto.setProductCatalogNumber(source.getProductCatalogNumber());
		dto.setQuantity(source.getQuantity());
		dto.setWorkingHours(source.getWorkingHours());
		dto.setNote(source.getNote());
		dto.setDate(source.getDate());

		ArrayList<Long> workerIds = new ArrayList<Long>();
		for (Worker w : source.getWorkers()) {
			workerIds.add(w.getId());
		}
		dto.setWorkerIds(workerIds);

		dto.setLocationId(source.getLocation().getId());
		dto.setInputTime(source.getInputTime());
		dto.setEnteredBy(source.getEnteredBy());

		return dto;
	}

	public List<ProductivityItemDTO> convert(List<ProductivityItem> sourceList) {
		List<ProductivityItemDTO> dtoList = new ArrayList<>();
		for (ProductivityItem p : sourceList) {
			dtoList.add(convert(p));
		}
		return dtoList;
	}

}
