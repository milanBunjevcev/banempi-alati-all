package rs.bane.alati.server.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "worker_id", "date" }, name = "uniquePresence") })
public class Presence {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column
	private double hours;

	@ManyToOne(optional = false)
	private Worker worker;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
		if (!worker.getPresences().contains(this)) {
			worker.getPresences().add(this);
		}
	}

}
