package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Propietario")
public class Propietario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPropietario;
	
	@Column(name="NPropietario", length=60, nullable=false)
	private String NPropietario;
	
	@Column(name="APropietario", length=60, nullable=false)
	private String APropietario;
	
	@Column(name="DNIPropietario", length=8, nullable=false)
	private int DNIPropietario;
	
	@Column(name="UsernamePropietario", length=60, nullable=false)
	private String UsernamePropietario;
	
	@Temporal(TemporalType.DATE)
	@Column(name ="DNacimientoPropietario")
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date DNacimientoPropietario;
	
	@Column(name="GeneroPropietario", length=12, nullable=false)
	private String GeneroPropietario;
	
	@Column(name="NroCelularPropietario", length=9, nullable=false)
	private int NroCelularPropietario;
	
	@Column(name="EmailPropietario", length=30, nullable=false)
	private String EmailPropietario;
	
	@Column(name="NacionalidadPropietario", length=30, nullable=false)
	private String NacionalidadPropietario;
	
	@Column(name="ContraseñaPropietario", length=10, nullable=false)
	private String ContraseñaPropietario;

	public Propietario() {
		super();
	}

	public Propietario(int idPropietario, String nPropietario, String aPropietario, int dNIPropietario,
			String usernamePropietario, Date dNacimientoPropietario, String generoPropietario,
			int nroCelularPropietario, String emailPropietario, String nacionalidadPropietario,
			String contraseñaPropietario) {
		super();
		this.idPropietario = idPropietario;
		NPropietario = nPropietario;
		APropietario = aPropietario;
		DNIPropietario = dNIPropietario;
		UsernamePropietario = usernamePropietario;
		DNacimientoPropietario = dNacimientoPropietario;
		GeneroPropietario = generoPropietario;
		NroCelularPropietario = nroCelularPropietario;
		EmailPropietario = emailPropietario;
		NacionalidadPropietario = nacionalidadPropietario;
		ContraseñaPropietario = contraseñaPropietario;
	}

	public int getIdPropietario() {
		return idPropietario;
	}

	public void setIdPropietario(int idPropietario) {
		this.idPropietario = idPropietario;
	}

	public String getNPropietario() {
		return NPropietario;
	}

	public void setNPropietario(String nPropietario) {
		NPropietario = nPropietario;
	}

	public String getAPropietario() {
		return APropietario;
	}

	public void setAPropietario(String aPropietario) {
		APropietario = aPropietario;
	}

	public int getDNIPropietario() {
		return DNIPropietario;
	}

	public void setDNIPropietario(int dNIPropietario) {
		DNIPropietario = dNIPropietario;
	}

	public String getUsernamePropietario() {
		return UsernamePropietario;
	}

	public void setUsernamePropietario(String usernamePropietario) {
		UsernamePropietario = usernamePropietario;
	}

	public Date getDNacimientoPropietario() {
		return DNacimientoPropietario;
	}

	public void setDNacimientoPropietario(Date dNacimientoPropietario) {
		DNacimientoPropietario = dNacimientoPropietario;
	}

	public String getGeneroPropietario() {
		return GeneroPropietario;
	}

	public void setGeneroPropietario(String generoPropietario) {
		GeneroPropietario = generoPropietario;
	}

	public int getNroCelularPropietario() {
		return NroCelularPropietario;
	}

	public void setNroCelularPropietario(int nroCelularPropietario) {
		NroCelularPropietario = nroCelularPropietario;
	}

	public String getEmailPropietario() {
		return EmailPropietario;
	}

	public void setEmailPropietario(String emailPropietario) {
		EmailPropietario = emailPropietario;
	}

	public String getNacionalidadPropietario() {
		return NacionalidadPropietario;
	}

	public void setNacionalidadPropietario(String nacionalidadPropietario) {
		NacionalidadPropietario = nacionalidadPropietario;
	}

	public String getContraseñaPropietario() {
		return ContraseñaPropietario;
	}

	public void setContraseñaPropietario(String contraseñaPropietario) {
		ContraseñaPropietario = contraseñaPropietario;
	}

	
}
