package rs.bane.alati.server.web.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductivityItemDTO {

	private Long id;
	private int workOrderNumber;
	private int techOperationNumber;
	private String operationName;
	private double techOutturn;
	private String productName;
	private String productCatalogNumber;
	private double quantity;
	private double workingHours;
	private String note;
	private Date date;

	private List<Long> workerIds = new ArrayList<Long>();

	private Long locationId;
	private String locationName;

	private Timestamp inputTime;
	private String enteredBy;

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

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
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

}
