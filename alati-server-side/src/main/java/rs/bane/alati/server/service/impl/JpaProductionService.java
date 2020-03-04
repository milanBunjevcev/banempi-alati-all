package rs.bane.alati.server.service.impl;

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
		return productionRepository.getOne(id);
	}

	@Override
	public List<Production> findAll() {
		return productionRepository.findAll();
	}

	@Override
	public Production save(Production production) {
		return productionRepository.save(production);
	}

	@Override
	public List<Production> save(List<Production> productions) {
		return productionRepository.save(productions);
	}

	@Override
	public Production delete(Long id) {
		Production toDelete = productionRepository.findOne(id);
		if (toDelete != null) {
			productionRepository.delete(id);
		}
		return toDelete;
	}

	@Override
	public void delete(List<Long> ids) {
		for (Long id : ids) {
			productionRepository.delete(id);
		}
	}

}
