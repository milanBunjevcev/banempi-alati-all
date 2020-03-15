package rs.bane.alati.server.web.dto;

import java.util.Date;

import javax.validation.constraints.DecimalMin;

public class PresenceDTO {

	private Long id;
	private Date date;
	@DecimalMin("0.01")
	private double hours;
	private Long workerId;

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

	public Long getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}

}
