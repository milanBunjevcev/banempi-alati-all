package rs.bane.alati.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.bane.alati.server.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
