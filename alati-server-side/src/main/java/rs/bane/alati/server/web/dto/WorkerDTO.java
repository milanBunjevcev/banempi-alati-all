package rs.bane.alati.server.web.dto;

import org.hibernate.validator.constraints.NotBlank;

import rs.bane.alati.server.model.ContractType;

public class WorkerDTO {

	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String lastName;
	private ContractType contractType;
	private boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ContractType getContractType() {
		return contractType;
	}

	public void setContractType(ContractType contractType) {
		this.contractType = contractType;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
