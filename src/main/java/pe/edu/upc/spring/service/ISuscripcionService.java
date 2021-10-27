package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Suscripcion;

public interface ISuscripcionService {
	public boolean grabar(Suscripcion suscripcion);
	public void eliminar(int idSuscripcion);
	public Optional<Suscripcion> listarId(int idSuscripcion);
	public List<Suscripcion> listar();
	public List<Suscripcion> buscarTipo(String TipoSuscripcion);
	public List<Suscripcion> buscarCosto(int CostoSuscripcion);
	public List<Suscripcion> buscarTipoCosto(String TipoSuscripcion, int CostoSuscripcion);
}
