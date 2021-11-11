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
@Table(name="SuscripcionXPropietario")
public class SuscripcionXPropietario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSuscripcionXRoomie;
	
	@Column(name="RenovableSuscripcionXPropietario", nullable=false)
	private Boolean RenovableSuscripcionXPropietario;
	
	@ManyToOne
	@JoinColumn(name ="idSuscripcion", nullable=false)
	private Suscripcion suscripcionSuscripcionXPropietario;
	
	@ManyToOne
	@JoinColumn(name ="idPropietario", nullable=false)
	private Propietario propietarioSuscripcionXPropietario;
	
	public SuscripcionXPropietario() {
		super();
	}

	public SuscripcionXPropietario(int idSuscripcionXRoomie, Boolean renovableSuscripcionXPropietario,
			Suscripcion suscripcionSuscripcionXPropietario, Propietario propietarioSuscripcionXPropietario) {
		super();
		this.idSuscripcionXRoomie = idSuscripcionXRoomie;
		RenovableSuscripcionXPropietario = renovableSuscripcionXPropietario;
		this.suscripcionSuscripcionXPropietario = suscripcionSuscripcionXPropietario;
		this.propietarioSuscripcionXPropietario = propietarioSuscripcionXPropietario;
	}

	public int getIdSuscripcionXRoomie() {
		return idSuscripcionXRoomie;
	}

	public void setIdSuscripcionXRoomie(int idSuscripcionXRoomie) {
		this.idSuscripcionXRoomie = idSuscripcionXRoomie;
	}

	public Boolean getRenovableSuscripcionXPropietario() {
		return RenovableSuscripcionXPropietario;
	}

	public void setRenovableSuscripcionXPropietario(Boolean renovableSuscripcionXPropietario) {
		RenovableSuscripcionXPropietario = renovableSuscripcionXPropietario;
	}

	public Suscripcion getSuscripcionSuscripcionXPropietario() {
		return suscripcionSuscripcionXPropietario;
	}

	public void setSuscripcionSuscripcionXPropietario(Suscripcion suscripcionSuscripcionXPropietario) {
		this.suscripcionSuscripcionXPropietario = suscripcionSuscripcionXPropietario;
	}

	public Propietario getPropietarioSuscripcionXPropietario() {
		return propietarioSuscripcionXPropietario;
	}

	public void setPropietarioSuscripcionXPropietario(Propietario propietarioSuscripcionXPropietario) {
		this.propietarioSuscripcionXPropietario = propietarioSuscripcionXPropietario;
	}

	
	
}
