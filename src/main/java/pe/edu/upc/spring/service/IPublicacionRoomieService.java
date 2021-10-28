package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.PublicacionRoomie;

public interface IPublicacionRoomieService {
	public boolean grabar(PublicacionRoomie  publicacionRoomie);
	public void eliminar(int idPublicacionRoomie);
	public Optional<PublicacionRoomie > listarId(int idPublicacionRoomie);
	public List<PublicacionRoomie > listar();
}
