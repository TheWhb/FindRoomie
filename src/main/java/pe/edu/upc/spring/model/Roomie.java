package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Roomie")
public class Roomie implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRoomie;
	
	@Column(name="NRoomie", length=60, nullable=false)
	private String NRoomie;
	
	@Column(name="ARoomie", length=60, nullable=false)
	private String ARoomie;
	
	@Column(name="DNIRoomie", length=8, nullable=false)
	private String DNIRoomie;
	
	@Column(name="UsernameRoomie", length=60, nullable=false)
	private String UsernameRoomie;
	
	@Temporal(TemporalType.DATE)
	@Column(name ="DNacimientoRoomie")
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date DNacimientoRoomie;
	
	@Column(name="GeneroRoomie", length=10, nullable=false)
	private String GeneroRoomie;
	
	@Column(name="NroCelularRoomie", length=9, nullable=false)
	private int NroCelularRoomie;
	
	@Column(name="EmailRoomie", length=30, nullable=false)
	private String EmailRoomie;
	
	@Column(name="NacionalidadRoomie", length=30, nullable=false)
	private String NacionalidadRoomie;

	@Column(name="PresentacionRoomie", length=200, nullable=false)
	private String PresentacionRoomie;
	
	@Column(name="ContraseñaRoomie", length=10, nullable=false)
	private String ContraseñaRoomie;
	
	@ManyToOne
	@JoinColumn(name ="idVivienda", nullable=true)
	private Vivienda viviendaRoomie;

	public Roomie() {
		super();
	}

	public Roomie(int idRoomie, String nRoomie, String aRoomie, String dNIRoomie, String usernameRoomie,
			Date dNacimientoRoomie, String generoRoomie, int nroCelularRoomie, String emailRoomie,
			String nacionalidadRoomie, String presentacionRoomie, String contraseñaRoomie, Vivienda viviendaRoomie) {
		super();
		this.idRoomie = idRoomie;
		NRoomie = nRoomie;
		ARoomie = aRoomie;
		DNIRoomie = dNIRoomie;
		UsernameRoomie = usernameRoomie;
		DNacimientoRoomie = dNacimientoRoomie;
		GeneroRoomie = generoRoomie;
		NroCelularRoomie = nroCelularRoomie;
		EmailRoomie = emailRoomie;
		NacionalidadRoomie = nacionalidadRoomie;
		PresentacionRoomie = presentacionRoomie;
		ContraseñaRoomie = contraseñaRoomie;
		this.viviendaRoomie = viviendaRoomie;
	}

	public int getIdRoomie() {
		return idRoomie;
	}

	public void setIdRoomie(int idRoomie) {
		this.idRoomie = idRoomie;
	}

	public String getNRoomie() {
		return NRoomie;
	}

	public void setNRoomie(String nRoomie) {
		NRoomie = nRoomie;
	}

	public String getARoomie() {
		return ARoomie;
	}

	public void setARoomie(String aRoomie) {
		ARoomie = aRoomie;
	}

	public String getDNIRoomie() {
		return DNIRoomie;
	}

	public void setDNIRoomie(String dNIRoomie) {
		DNIRoomie = dNIRoomie;
	}

	public String getUsernameRoomie() {
		return UsernameRoomie;
	}

	public void setUsernameRoomie(String usernameRoomie) {
		UsernameRoomie = usernameRoomie;
	}

	public Date getDNacimientoRoomie() {
		return DNacimientoRoomie;
	}

	public void setDNacimientoRoomie(Date dNacimientoRoomie) {
		DNacimientoRoomie = dNacimientoRoomie;
	}

	public String getGeneroRoomie() {
		return GeneroRoomie;
	}

	public void setGeneroRoomie(String generoRoomie) {
		GeneroRoomie = generoRoomie;
	}

	public int getNroCelularRoomie() {
		return NroCelularRoomie;
	}

	public void setNroCelularRoomie(int nroCelularRoomie) {
		NroCelularRoomie = nroCelularRoomie;
	}

	public String getEmailRoomie() {
		return EmailRoomie;
	}

	public void setEmailRoomie(String emailRoomie) {
		EmailRoomie = emailRoomie;
	}

	public String getNacionalidadRoomie() {
		return NacionalidadRoomie;
	}

	public void setNacionalidadRoomie(String nacionalidadRoomie) {
		NacionalidadRoomie = nacionalidadRoomie;
	}

	public String getPresentacionRoomie() {
		return PresentacionRoomie;
	}

	public void setPresentacionRoomie(String presentacionRoomie) {
		PresentacionRoomie = presentacionRoomie;
	}

	public String getContraseñaRoomie() {
		return ContraseñaRoomie;
	}

	public void setContraseñaRoomie(String contraseñaRoomie) {
		ContraseñaRoomie = contraseñaRoomie;
	}

	public Vivienda getViviendaRoomie() {
		return viviendaRoomie;
	}

	public void setViviendaRoomie(Vivienda viviendaRoomie) {
		this.viviendaRoomie = viviendaRoomie;
	}
	
}
