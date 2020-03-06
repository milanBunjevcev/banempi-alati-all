package rs.bane.alati.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rs.bane.alati.server.model.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

	

	@Query("SELECT w FROM Worker w WHERE " 
			+ "("
			+ "(:name IS NULL or w.name like :name ) AND "
			+ "(:lastName IS NULL or w.lastName like :lastName )"
			+ ") OR "
			+ "("
			+ "(:lastName IS NULL or w.name like :lastName ) AND "
			+ "(:name IS NULL or w.lastName like :name )"
			+ ")")
	Page<Worker> findByNameLikeAndLastNameLike(@Param("name") String name, @Param("lastName") String lastName, Pageable pageRequest);

}
