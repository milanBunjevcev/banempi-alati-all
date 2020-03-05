package rs.bane.alati.server;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rs.bane.alati.server.model.Location;
import rs.bane.alati.server.model.Production;
import rs.bane.alati.server.model.Worker;
import rs.bane.alati.server.service.LocationService;
import rs.bane.alati.server.service.ProductionService;
import rs.bane.alati.server.service.WorkerService;

@Component
public class TestDada {

	@Autowired
	private WorkerService workerService;

	@Autowired
	private LocationService locationService;

	@Autowired
	private ProductionService productionService;

	@PostConstruct
	public void init() {
		Random rnd = new Random();

		for (int i = 1; i <= 5; i++) {
			int n = rnd.nextInt(Worker.ContractType.values().length);
			String ime = "Ime-" + i;
			String prezime = "Prezime-" + i;
			Worker.ContractType ugovor = Worker.ContractType.values()[n];

			workerService.save(new Worker(ime, prezime, ugovor));
		}

		Location location = new Location("pogon1");
		locationService.save(location);
		location = new Location("pogon2");
		locationService.save(location);

		Production prod1 = new Production(82321, 20, "oper1", 230, "naziv art", "1301", 222, 8, "", new Date(),
				location, new Timestamp(new Date().getTime()), "milan");
		Worker worker = workerService.findOne(2L);
		prod1.addWorker(worker);
		productionService.save(prod1);

	}

}
