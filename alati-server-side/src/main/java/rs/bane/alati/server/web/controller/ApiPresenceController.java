package rs.bane.alati.server.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.bane.alati.server.model.Presence;
import rs.bane.alati.server.service.PresenceService;
import rs.bane.alati.server.support.dto.PresenceDTOToPresence;
import rs.bane.alati.server.support.dto.PresenceToPresenceDTO;
import rs.bane.alati.server.web.dto.PresenceDTO;

@RestController
@RequestMapping(value = "/api/workers/presence")
public class ApiPresenceController {

	@Autowired
	private PresenceService presenceService;

	@Autowired
	private PresenceToPresenceDTO toPresenceDto;

	@Autowired
	private PresenceDTOToPresence toPresence;

	@RequestMapping(method = RequestMethod.GET, value = "/{date}/{id}")
	ResponseEntity<PresenceDTO> getPresenceForDateAndWorkerId(@PathVariable @DateTimeFormat(iso = ISO.DATE) Date date,
			@PathVariable Long id) {
		Presence prisustvo = presenceService.findByDateAndWorkerId(date, id);
		if (prisustvo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PresenceDTO>(toPresenceDto.convert(prisustvo), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	ResponseEntity<PresenceDTO> add(@RequestBody @Validated PresenceDTO newPresenceDTO) {
		Presence newPresence = presenceService.save(toPresence.convert(newPresenceDTO));
		return new ResponseEntity<PresenceDTO>(toPresenceDto.convert(newPresence), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	ResponseEntity<PresenceDTO> delete(@PathVariable Long id) {
		Presence deleted = presenceService.delete(id);
		if (deleted == null) {
			return new ResponseEntity<PresenceDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PresenceDTO>(toPresenceDto.convert(deleted), HttpStatus.OK);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
