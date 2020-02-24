package rs.bane.alati.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.bane.alati.server.model.worker.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

	List<Worker> findByName(String name);

	List<Worker> findByLastName(String lastName);

	List<Worker> findByNameAndLastName(String name, String lastName);

}
