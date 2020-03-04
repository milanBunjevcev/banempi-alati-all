package rs.bane.alati.server.service;

import java.util.List;

import rs.bane.alati.server.model.Production;

public interface ProductionService {
	Production findOne(Long id);

	List<Production> findAll();

	Production save(Production production);

	List<Production> save(List<Production> productions);

	Production delete(Long id);

	void delete(List<Long> ids);

}
