package rs.bane.alati.server.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.bane.alati.server.model.Production;

@Repository
public interface ProductionRepository extends JpaRepository<Production, Long> {

	List<Production> findByDatumUcinkaBetween(Date datumUcinka1, Date datumUcinka2);

}
