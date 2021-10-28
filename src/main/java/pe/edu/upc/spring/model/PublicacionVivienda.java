package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="PublicacionVivienda")
public class PublicacionVivienda implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPublicacionVivienda;
	
	@Column(name="CompartirPublicacionVivienda", nullable=false)
	private boolean CompartirPublicacionVivienda;
	
	@OneToOne
	@JoinColumn(name ="idVivienda", nullable=false)
	private Vivienda viviendapublicacionvivienda;

	public PublicacionVivienda() {
		super();
	}

	public PublicacionVivienda(int idPublicacionVivienda, boolean compartirPublicacionVivienda,
			Vivienda viviendapublicacionvivienda) {
		super();
		this.idPublicacionVivienda = idPublicacionVivienda;
		CompartirPublicacionVivienda = compartirPublicacionVivienda;
		this.viviendapublicacionvivienda = viviendapublicacionvivienda;
	}

	public int getIdPublicacionVivienda() {
		return idPublicacionVivienda;
	}

	public void setIdPublicacionVivienda(int idPublicacionVivienda) {
		this.idPublicacionVivienda = idPublicacionVivienda;
	}

	public boolean isCompartirPublicacionVivienda() {
		return CompartirPublicacionVivienda;
	}

	public void setCompartirPublicacionVivienda(boolean compartirPublicacionVivienda) {
		CompartirPublicacionVivienda = compartirPublicacionVivienda;
	}

	public Vivienda getViviendapublicacionvivienda() {
		return viviendapublicacionvivienda;
	}

	public void setViviendapublicacionvivienda(Vivienda viviendapublicacionvivienda) {
		this.viviendapublicacionvivienda = viviendapublicacionvivienda;
	}

	
	
}
