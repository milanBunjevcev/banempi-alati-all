package rs.bane.alati.server.web.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductionDTO {

	private Long id;
	private int brrn;
	private int broj;
	private String nazivOperacije;
	private double norma;
	private String nazivArtikla;
	private String kataloskiBroj;
	private double proizvedenoKolicina;
	private double utrosenoVreme;
	private String napomena;
	private Date datumUcinka;

	private List<Long> workerIds = new ArrayList<Long>();

	private Long locationId;

	private Timestamp vremeUnosaUcinka;
	private String uneoKorisnik;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBrrn() {
		return brrn;
	}

	public void setBrrn(int brrn) {
		this.brrn = brrn;
	}

	public int getBroj() {
		return broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

	public String getNazivOperacije() {
		return nazivOperacije;
	}

	public void setNazivOperacije(String nazivOperacije) {
		this.nazivOperacije = nazivOperacije;
	}

	public double getNorma() {
		return norma;
	}

	public void setNorma(double norma) {
		this.norma = norma;
	}

	public String getNazivArtikla() {
		return nazivArtikla;
	}

	public void setNazivArtikla(String nazivArtikla) {
		this.nazivArtikla = nazivArtikla;
	}

	public String getKataloskiBroj() {
		return kataloskiBroj;
	}

	public void setKataloskiBroj(String kataloskiBroj) {
		this.kataloskiBroj = kataloskiBroj;
	}

	public double getProizvedenoKolicina() {
		return proizvedenoKolicina;
	}

	public void setProizvedenoKolicina(double proizvedenoKolicina) {
		this.proizvedenoKolicina = proizvedenoKolicina;
	}

	public double getUtrosenoVreme() {
		return utrosenoVreme;
	}

	public void setUtrosenoVreme(double utrosenoVreme) {
		this.utrosenoVreme = utrosenoVreme;
	}

	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	public Date getDatumUcinka() {
		return datumUcinka;
	}

	public void setDatumUcinka(Date datumUcinka) {
		this.datumUcinka = datumUcinka;
	}

	public List<Long> getWorkerIds() {
		return workerIds;
	}

	public void setWorkerIds(List<Long> workerIds) {
		this.workerIds = workerIds;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public Timestamp getVremeUnosaUcinka() {
		return vremeUnosaUcinka;
	}

	public void setVremeUnosaUcinka(Timestamp vremeUnosaUcinka) {
		this.vremeUnosaUcinka = vremeUnosaUcinka;
	}

	public String getUneoKorisnik() {
		return uneoKorisnik;
	}

	public void setUneoKorisnik(String uneoKorisnik) {
		this.uneoKorisnik = uneoKorisnik;
	}

}
