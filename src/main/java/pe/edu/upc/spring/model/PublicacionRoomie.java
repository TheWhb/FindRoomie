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
@Table(name="PublicacionRoomie")
public class PublicacionRoomie implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPublicacionRoomie;
	
	@Column(name="CompartirPublicacionRoomie", nullable=false)
	private boolean CompartirPublicacionRoomie;
	
	@OneToOne
	@JoinColumn(name ="idRoomie", nullable=false)
	private Roomie roomiePublicacionRoomie;

	public PublicacionRoomie() {
		super();
	}

	public PublicacionRoomie(int idPublicacionRoomie, boolean compartirPublicacionRoomie,
			Roomie roomiePublicacionRoomie) {
		super();
		this.idPublicacionRoomie = idPublicacionRoomie;
		CompartirPublicacionRoomie = compartirPublicacionRoomie;
		this.roomiePublicacionRoomie = roomiePublicacionRoomie;
	}

	public int getIdPublicacionRoomie() {
		return idPublicacionRoomie;
	}

	public void setIdPublicacionRoomie(int idPublicacionRoomie) {
		this.idPublicacionRoomie = idPublicacionRoomie;
	}

	public boolean isCompartirPublicacionRoomie() {
		return CompartirPublicacionRoomie;
	}

	public void setCompartirPublicacionRoomie(boolean compartirPublicacionRoomie) {
		CompartirPublicacionRoomie = compartirPublicacionRoomie;
	}

	public Roomie getRoomiePublicacionRoomie() {
		return roomiePublicacionRoomie;
	}

	public void setRoomiePublicacionRoomie(Roomie roomiePublicacionRoomie) {
		this.roomiePublicacionRoomie = roomiePublicacionRoomie;
	}
	
}
