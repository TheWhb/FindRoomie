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
	private String DNIPropietario;
	
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
	
	@Column(name="EmailPropietario", length=30, nullable=false /*, unique = true*/)
	private String EmailPropietario;
	
	@Column(name="NacionalidadPropietario", length=30, nullable=false)
	private String NacionalidadPropietario;
	
	@Column(name="ContrasenaPropietario", length=70, nullable=false)
	private String ContrasenaPropietario;

	public Propietario() {
		super();
	}

	public Propietario(int idPropietario, String nPropietario, String aPropietario, String dNIPropietario,
			String usernamePropietario, Date dNacimientoPropietario, String generoPropietario,
			int nroCelularPropietario, String emailPropietario, String nacionalidadPropietario,
			String contrasenaPropietario) {
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
		ContrasenaPropietario = contrasenaPropietario;
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

	public String getDNIPropietario() {
		return DNIPropietario;
	}

	public void setDNIPropietario(String dNIPropietario) {
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

	public String getContrasenaPropietario() {
		return ContrasenaPropietario;
	}

	public void setContrasenaPropietario(String contrasenaPropietario) {
		ContrasenaPropietario = contrasenaPropietario;
	}

	

	

	
}
