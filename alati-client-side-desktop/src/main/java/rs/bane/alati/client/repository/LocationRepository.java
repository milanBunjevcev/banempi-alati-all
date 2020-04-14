package rs.bane.alati.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.bane.alati.client.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
