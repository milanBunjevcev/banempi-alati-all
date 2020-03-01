package rs.bane.alati.server.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column
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
	@Column
	@Temporal(TemporalType.DATE)
	private Date datumUcinka;

	@ManyToMany
	@JoinTable(uniqueConstraints = {
			@UniqueConstraint(columnNames = { "productions_id", "workers_id" }, name = "uniqueRelation") })
	private List<Worker> workers;

	@ManyToOne
	private Location location;

	@Column
	private Timestamp vremeUnosaUcinka;
	@Column
	private String uneoKorisnik;

}
