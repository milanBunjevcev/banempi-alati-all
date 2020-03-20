package rs.bane.alati.server.support.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import rs.bane.alati.server.model.Production;
import rs.bane.alati.server.model.Worker;
import rs.bane.alati.server.web.dto.ProductionDTO;

@Component
public class ProductionToProductionDTO implements Converter<Production, ProductionDTO> {

	@Override
	public ProductionDTO convert(Production source) {
		if (source == null) {
			return null;
		}

		ProductionDTO dto = new ProductionDTO();

		dto.setId(source.getId());
		dto.setBrrn(source.getBrrn());
		dto.setBroj(source.getBroj());
		dto.setNazivOperacije(source.getNazivOperacije());
		dto.setNorma(source.getNorma());
		dto.setNazivArtikla(source.getNazivArtikla());
		dto.setKataloskiBroj(source.getKataloskiBroj());
		dto.setProizvedenoKolicina(source.getProizvedenoKolicina());
		dto.setUtrosenoVreme(source.getUtrosenoVreme());
		dto.setNapomena(source.getNapomena());
		dto.setDatumUcinka(source.getDatumUcinka());

		ArrayList<Long> workerIds = new ArrayList<Long>();
		for (Worker w : source.getWorkers()) {
			workerIds.add(w.getId());
		}
		dto.setWorkerIds(workerIds);

		dto.setLocationId(source.getLocation().getId());
		dto.setVremeUnosaUcinka(source.getVremeUnosaUcinka());
		dto.setUneoKorisnik(source.getUneoKorisnik());

		return dto;
	}

	public List<ProductionDTO> convert(List<Production> sourceList) {
		List<ProductionDTO> dtoList = new ArrayList<>();
		for (Production p : sourceList) {
			dtoList.add(convert(p));
		}
		return dtoList;
	}

}
