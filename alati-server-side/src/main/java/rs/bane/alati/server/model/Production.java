package rs.bane.alati.server.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table
public class Production {

	@Id
	@GeneratedValue
	private Long id;
	@Column
	private int brrn;
	@Column
	private int broj;
	@Column(nullable = false)
	private String nazivOperacije;
	@Column
	private double norma;
	@Column
	private String nazivArtikla;
	@Column
	private String kataloskiBroj;
	@Column
	private double proizvedenoKolicina;
	@Column
	private double utrosenoVreme;
	@Column
	private String napomena;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date datumUcinka;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(uniqueConstraints = {
			@UniqueConstraint(columnNames = { "production_id", "workers_id" }, name = "uniqueRelation") })
	private List<Worker> workers = new ArrayList<Worker>();

	@Column(nullable = false)
	@ManyToOne
	private Location location;

	@Column(nullable = false)
	private Timestamp vremeUnosaUcinka;
	@Column
	private String uneoKorisnik;

	public Production() {

	}

	public Production(int brrn, int broj, String nazivOperacije, double norma, String nazivArtikla,
			String kataloskiBroj, double proizvedenoKolicina, double utrosenoVreme, String napomena, Date datumUcinka,
			Location location, Timestamp vremeUnosaUcinka, String uneoKorisnik) {
		super();
		this.brrn = brrn;
		this.broj = broj;
		this.nazivOperacije = nazivOperacije;
		this.norma = norma;
		this.nazivArtikla = nazivArtikla;
		this.kataloskiBroj = kataloskiBroj;
		this.proizvedenoKolicina = proizvedenoKolicina;
		this.utrosenoVreme = utrosenoVreme;
		this.napomena = napomena;
		this.datumUcinka = datumUcinka;
		this.location = location;
		this.vremeUnosaUcinka = vremeUnosaUcinka;
		this.uneoKorisnik = uneoKorisnik;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Production other = (Production) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

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

	public List<Worker> getWorkers() {
		return workers;
	}

	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
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

	public void addWorker(Worker worker) {
		this.workers.add(worker);
	}

}
