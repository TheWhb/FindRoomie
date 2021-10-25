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
@Table(name="Vivienda")
public class Vivienda implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVivienda;
	
	@Column(name="NNombreVivienda", length=60, nullable=false)
	private String NNombreVivienda;
	
	@Column(name="DDireccionVivienda", length=100, nullable=false)
	private String DDireccionVivienda;
	
	@Column(name="NCuartosVivienda", length=2, nullable=false)
	private int NCuartosVivienda;
	
	@Column(name="NBanosVivienda", length=2, nullable=false)
	private int NBanosVivienda;

	@Column(name="NPisosVivienda", length=2, nullable=false)
	private int NPisosVivienda;
	
	@Column(name="NPersonasVivienda", length=2, nullable=false)
	private int NPersonasVivienda;
	
	@ManyToOne
	@JoinColumn(name ="idPropietario", nullable=false)
	private Propietario propietarioVivienda;
	
	public Vivienda() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vivienda(int idVivienda, String nNombreVivienda, String dDireccionVivienda, int nCuartosVivienda,
			int nBanosVivienda, int nPisosVivienda, int nPersonasVivienda, Propietario propietarioVivienda) {
		super();
		this.idVivienda = idVivienda;
		NNombreVivienda = nNombreVivienda;
		DDireccionVivienda = dDireccionVivienda;
		NCuartosVivienda = nCuartosVivienda;
		NBanosVivienda = nBanosVivienda;
		NPisosVivienda = nPisosVivienda;
		NPersonasVivienda = nPersonasVivienda;
		this.propietarioVivienda = propietarioVivienda;
	}

	public int getIdVivienda() {
		return idVivienda;
	}

	public void setIdVivienda(int idVivienda) {
		this.idVivienda = idVivienda;
	}

	public String getNNombreVivienda() {
		return NNombreVivienda;
	}

	public void setNNombreVivienda(String nNombreVivienda) {
		NNombreVivienda = nNombreVivienda;
	}

	public String getDDireccionVivienda() {
		return DDireccionVivienda;
	}

	public void setDDireccionVivienda(String dDireccionVivienda) {
		DDireccionVivienda = dDireccionVivienda;
	}

	public int getNCuartosVivienda() {
		return NCuartosVivienda;
	}

	public void setNCuartosVivienda(int nCuartosVivienda) {
		NCuartosVivienda = nCuartosVivienda;
	}

	public int getNBanosVivienda() {
		return NBanosVivienda;
	}

	public void setNBanosVivienda(int nBanosVivienda) {
		NBanosVivienda = nBanosVivienda;
	}

	public int getNPisosVivienda() {
		return NPisosVivienda;
	}

	public void setNPisosVivienda(int nPisosVivienda) {
		NPisosVivienda = nPisosVivienda;
	}

	public int getNPersonasVivienda() {
		return NPersonasVivienda;
	}

	public void setNPersonasVivienda(int nPersonasVivienda) {
		NPersonasVivienda = nPersonasVivienda;
	}

	public Propietario getPropietarioVivienda() {
		return propietarioVivienda;
	}

	public void setPropietarioVivienda(Propietario propietarioVivienda) {
		this.propietarioVivienda = propietarioVivienda;
	}
	
	

	
	
	
}
