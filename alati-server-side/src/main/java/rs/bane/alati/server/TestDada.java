package rs.bane.alati.server;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rs.bane.alati.server.model.Worker;
import rs.bane.alati.server.service.WorkerService;

@Component
public class TestDada {

	@Autowired
	private WorkerService workerService;

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
		
		
	}

}
