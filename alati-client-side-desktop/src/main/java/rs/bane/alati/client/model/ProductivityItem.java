package rs.bane.alati.client.model;

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
public class ProductivityItem {

	@Id
	@GeneratedValue
	private Long id;
	@Column
	private int workOrderNumber;
	@Column
	private int techOperationNumber;
	@Column(nullable = false)
	private String operationName;
	@Column
	private double techOutturn;
	@Column
	private String productName;
	@Column
	private String productCatalogNumber;
	@Column
	private double quantity;
	@Column
	private double workingHours;
	@Column
	private String note;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date date;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(uniqueConstraints = {
			@UniqueConstraint(columnNames = { "productivity_item_id", "workers_id" }, name = "uniqueRelation") })
	private List<Worker> workers = new ArrayList<Worker>();

	@ManyToOne(optional = false)
	private Location location;

	@Column(nullable = false)
	private Timestamp inputTime;
	@Column
	private String enteredBy;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductivityItem other = (ProductivityItem) obj;
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

	public int getWorkOrderNumber() {
		return workOrderNumber;
	}

	public void setWorkOrderNumber(int brrn) {
		this.workOrderNumber = brrn;
	}

	public int getTechOperationNumber() {
		return techOperationNumber;
	}

	public void setTechOperationNumber(int broj) {
		this.techOperationNumber = broj;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String nazivOperacije) {
		this.operationName = nazivOperacije;
	}

	public double getTechOutturn() {
		return techOutturn;
	}

	public void setTechOutturn(double norma) {
		this.techOutturn = norma;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String nazivArtikla) {
		this.productName = nazivArtikla;
	}

	public String getProductCatalogNumber() {
		return productCatalogNumber;
	}

	public void setProductCatalogNumber(String kataloskiBroj) {
		this.productCatalogNumber = kataloskiBroj;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double proizvedenoKolicina) {
		this.quantity = proizvedenoKolicina;
	}

	public double getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(double utrosenoVreme) {
		this.workingHours = utrosenoVreme;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String napomena) {
		this.note = napomena;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date datumUcinka) {
		this.date = datumUcinka;
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

	public Timestamp getInputTime() {
		return inputTime;
	}

	public void setInputTime(Timestamp vremeUnosaUcinka) {
		this.inputTime = vremeUnosaUcinka;
	}

	public String getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(String uneoKorisnik) {
		this.enteredBy = uneoKorisnik;
	}

	public void addWorker(Worker worker) {
		this.workers.add(worker);
	}

}
