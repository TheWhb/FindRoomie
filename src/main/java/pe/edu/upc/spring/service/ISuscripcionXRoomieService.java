package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.SuscripcionXRoomie;

public interface ISuscripcionXRoomieService {
	public boolean grabar(SuscripcionXRoomie suscripcionXRoomie);
	public void eliminar(int idSuscripcionXRoomie);
	public Optional<SuscripcionXRoomie> listarId(int idSuscripcionXRoomie);
	public List<SuscripcionXRoomie> listar();
}
