package rs.bane.alati.server.service;

import java.util.Date;
import java.util.List;

import rs.bane.alati.server.model.Production;

public interface ProductionService {
	Production findOne(Long id);

	List<Production> findAll();

	List<Production> findByDatumUcinkaBetween(Date datumUcinka1, Date datumUcinka2);

	Production save(Production production);

	Production delete(Long id);

}
