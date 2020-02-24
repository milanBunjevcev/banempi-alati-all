package rs.bane.alati.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Lokacija {

	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "naziv_lokacije")
	private String nazivLokacije;

	public Lokacija() {

	}

	public Lokacija(String nazivLokacije) {
		this.nazivLokacije = nazivLokacije;
	}

	public Lokacija(Long id, String nazivLokacije) {
		this.id = id;
		this.nazivLokacije = nazivLokacije;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lokacija other = (Lokacija) obj;
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

	public String getNazivLokacije() {
		return nazivLokacije;
	}

	public void setNazivLokacije(String nazivLokacije) {
		this.nazivLokacije = nazivLokacije;
	}

}
