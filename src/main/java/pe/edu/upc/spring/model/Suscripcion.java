package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Suscripcion")
public class Suscripcion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSuscripcion;
	
	@Column(name="TipoSuscripcion", length=60, nullable=false)
	private String TipoSuscripcion;
	
	@Column(name="CostoSuscripcion", nullable=false)
	private int CostoSuscripcion;
	
	public Suscripcion() {
		super();
	}

	public Suscripcion(int idSuscripcion, String tipoSuscripcion, int costoSuscripcion) {
		super();
		this.idSuscripcion = idSuscripcion;
		TipoSuscripcion = tipoSuscripcion;
		CostoSuscripcion = costoSuscripcion;
	}

	public int getIdSuscripcion() {
		return idSuscripcion;
	}

	public void setIdSuscripcion(int idSuscripcion) {
		this.idSuscripcion = idSuscripcion;
	}

	public String getTipoSuscripcion() {
		return TipoSuscripcion;
	}

	public void setTipoSuscripcion(String tipoSuscripcion) {
		TipoSuscripcion = tipoSuscripcion;
	}

	public int getCostoSuscripcion() {
		return CostoSuscripcion;
	}

	public void setCostoSuscripcion(int costoSuscripcion) {
		CostoSuscripcion = costoSuscripcion;
	}
	
}
