package rs.bane.alati.server.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.bane.alati.server.model.ProductivityItem;
import rs.bane.alati.server.service.ProductivityItemService;
import rs.bane.alati.server.support.dto.ProductivityItemToProductivityItemDTO;
import rs.bane.alati.server.web.dto.ProductivityItemDTO;

@RestController
@RequestMapping(value = "/api/productions")
public class ApiProductionController {

	@Autowired
	private ProductivityItemService productionService;

	@Autowired
	private ProductivityItemToProductivityItemDTO toDto;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	ResponseEntity<ProductivityItemDTO> getProduction(@PathVariable Long id) {
		ProductivityItem production = productionService.findOne(id);
		if (production == null) {
			return new ResponseEntity<ProductivityItemDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ProductivityItemDTO>(toDto.convert(production), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<ProductivityItemDTO>> getProduction(
			@RequestParam(value = "datumUcinka1") @DateTimeFormat(iso = ISO.DATE) Date datumUcinka1,
			@RequestParam(value = "datumUcinka2") @DateTimeFormat(iso = ISO.DATE) Date datumUcinka2) {
		List<ProductivityItem> productionList = productionService.findByDateBetween(datumUcinka1, datumUcinka2);
		return new ResponseEntity<>(toDto.convert(productionList), HttpStatus.OK);
	}

}
