package rs.bane.alati.server.support.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import rs.bane.alati.server.model.ProductivityItem;
import rs.bane.alati.server.model.Worker;
import rs.bane.alati.server.web.dto.ProductionDTO;

@Component
public class ProductionToProductionDTO implements Converter<ProductivityItem, ProductionDTO> {

	@Override
	public ProductionDTO convert(ProductivityItem source) {
		if (source == null) {
			return null;
		}

		ProductionDTO dto = new ProductionDTO();

		dto.setId(source.getId());
		dto.setBrrn(source.getWorkOrderNumber());
		dto.setBroj(source.getTechOperationNumber());
		dto.setNazivOperacije(source.getOperationName());
		dto.setNorma(source.getTechOutturn());
		dto.setNazivArtikla(source.getProductName());
		dto.setKataloskiBroj(source.getProductCatalogNumber());
		dto.setProizvedenoKolicina(source.getQuantity());
		dto.setUtrosenoVreme(source.getWorkingHours());
		dto.setNapomena(source.getNote());
		dto.setDatumUcinka(source.getDate());

		ArrayList<Long> workerIds = new ArrayList<Long>();
		for (Worker w : source.getWorkers()) {
			workerIds.add(w.getId());
		}
		dto.setWorkerIds(workerIds);

		dto.setLocationId(source.getLocation().getId());
		dto.setVremeUnosaUcinka(source.getInputTime());
		dto.setUneoKorisnik(source.getEnteredBy());

		return dto;
	}

	public List<ProductionDTO> convert(List<ProductivityItem> sourceList) {
		List<ProductionDTO> dtoList = new ArrayList<>();
		for (ProductivityItem p : sourceList) {
			dtoList.add(convert(p));
		}
		return dtoList;
	}

}
