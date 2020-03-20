package rs.bane.alati.server.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.bane.alati.server.model.Production;
import rs.bane.alati.server.repository.ProductionRepository;
import rs.bane.alati.server.service.ProductionService;

@Service
@Transactional
public class JpaProductionService implements ProductionService {

	@Autowired
	private ProductionRepository productionRepository;

	@Override
	public Production findOne(Long id) {
		return productionRepository.findOne(id);
	}

	@Override
	public List<Production> findAll() {
		return productionRepository.findAll();
	}

	@Override
	public List<Production> findByDatumUcinkaBetween(Date datumUcinka1, Date datumUcinka2) {
		return productionRepository.findByDatumUcinkaBetween(datumUcinka1, datumUcinka2);
	}

	@Override
	public Production save(Production production) {
		return productionRepository.save(production);
	}

	@Override
	public Production delete(Long id) {
		Production toDelete = productionRepository.findOne(id);
		if (toDelete != null) {
			productionRepository.delete(id);
		}
		return toDelete;
	}

}
