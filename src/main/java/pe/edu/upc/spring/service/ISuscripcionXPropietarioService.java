package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.SuscripcionXPropietario;

public interface ISuscripcionXPropietarioService {
	public boolean grabar(SuscripcionXPropietario suscripcionXPropietario);
	public void eliminar(int idSuscripcionXPropietario);
	public Optional<SuscripcionXPropietario> listarId(int idSuscripcionXPropietario);
	public List<SuscripcionXPropietario> listar();
	public List<SuscripcionXPropietario> buscarIdPropietario(int idPropietario);
}
