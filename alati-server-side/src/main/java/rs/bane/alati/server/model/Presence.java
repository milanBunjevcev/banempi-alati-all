package rs.bane.alati.server.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Presence implements Serializable {

	private static final long serialVersionUID = 753020241989001950L;

	@Id
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column()
	private double hours;

	@Id
	@ManyToOne(optional = false)
	private Worker worker;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

}
