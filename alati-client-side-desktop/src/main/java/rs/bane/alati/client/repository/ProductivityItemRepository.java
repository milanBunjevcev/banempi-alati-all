package rs.bane.alati.client.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.bane.alati.client.model.ProductivityItem;

@Repository
public interface ProductivityItemRepository extends JpaRepository<ProductivityItem, Long> {

	List<ProductivityItem> findByDateBetween(Date date1, Date date2);

}
