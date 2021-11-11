package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Roomie;

public interface IRoomieService {
	public boolean grabar(Roomie roomie);
	public void eliminar(int idRoomie);
	public Optional<Roomie> listarId(int idRoomie);
	public List<Roomie> listar();
	public List<Roomie> findByEmailAndPassword(String EmailRoomie, String ContraseñaRoomie);
}
