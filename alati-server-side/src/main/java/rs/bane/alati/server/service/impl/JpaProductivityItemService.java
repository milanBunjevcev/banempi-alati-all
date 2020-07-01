package rs.bane.alati.server.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.bane.alati.server.model.ProductivityItem;
import rs.bane.alati.server.repository.ProductivityItemRepository;
import rs.bane.alati.server.service.ProductivityItemService;

@Service
@Transactional
public class JpaProductivityItemService implements ProductivityItemService {

	@Autowired
	private ProductivityItemRepository productivityItemRepository;

	@Override
	public ProductivityItem findOne(Long id) {
		return productivityItemRepository.findOne(id);
	}

	@Override
	public List<ProductivityItem> findAll() {
		return productivityItemRepository.findAll();
	}

	@Override
	public List<ProductivityItem> findByDateBetween(Date date1, Date date2) {
		return productivityItemRepository.findByDateBetween(date1, date2);
	}

	@Override
	public ProductivityItem save(ProductivityItem producitvityItem) {
		return productivityItemRepository.save(producitvityItem);
	}

	@Override
	public ProductivityItem delete(Long id) {
		ProductivityItem toDelete = productivityItemRepository.findOne(id);
		if (toDelete != null) {
			productivityItemRepository.delete(id);
		}
		return toDelete;
	}

}
