package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SuscripcionXRoomie")
public class SuscripcionXRoomie implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSuscripcionXRoomie;
	
	@Column(name="RenovableSuscripcionXRoomie", nullable=false)
	private Boolean RenovableSuscripcionXRoomie;
	
	@ManyToOne
	@JoinColumn(name ="idSuscripcion", nullable=false)
	private Suscripcion suscripcionSuscripcionXRoomie;
	
	@ManyToOne
	@JoinColumn(name ="idRoomie", nullable=false)
	private Roomie roomieSuscripcionXRoomie;
	
	public SuscripcionXRoomie() {
		super();
	}

	public SuscripcionXRoomie(int idSuscripcionXRoomie, Boolean renovableSuscripcionXRoomie,
			Suscripcion suscripcionSuscripcionXRoomie, Roomie roomieSuscripcionXRoomie) {
		super();
		this.idSuscripcionXRoomie = idSuscripcionXRoomie;
		RenovableSuscripcionXRoomie = renovableSuscripcionXRoomie;
		this.suscripcionSuscripcionXRoomie = suscripcionSuscripcionXRoomie;
		this.roomieSuscripcionXRoomie = roomieSuscripcionXRoomie;
	}

	public int getIdSuscripcionXRoomie() {
		return idSuscripcionXRoomie;
	}

	public void setIdSuscripcionXRoomie(int idSuscripcionXRoomie) {
		this.idSuscripcionXRoomie = idSuscripcionXRoomie;
	}

	public Boolean getRenovableSuscripcionXRoomie() {
		return RenovableSuscripcionXRoomie;
	}

	public void setRenovableSuscripcionXRoomie(Boolean renovableSuscripcionXRoomie) {
		RenovableSuscripcionXRoomie = renovableSuscripcionXRoomie;
	}

	public Suscripcion getSuscripcionSuscripcionXRoomie() {
		return suscripcionSuscripcionXRoomie;
	}

	public void setSuscripcionSuscripcionXRoomie(Suscripcion suscripcionSuscripcionXRoomie) {
		this.suscripcionSuscripcionXRoomie = suscripcionSuscripcionXRoomie;
	}

	public Roomie getRoomieSuscripcionXRoomie() {
		return roomieSuscripcionXRoomie;
	}

	public void setRoomieSuscripcionXRoomie(Roomie roomieSuscripcionXRoomie) {
		this.roomieSuscripcionXRoomie = roomieSuscripcionXRoomie;
	}
	
}
