package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Propietario;

public interface IPropietarioService {
	public boolean grabar(Propietario propietario);
	public void eliminar(int idPropietario);
	public Optional<Propietario> listarId(int idPropietario);
	public List<Propietario> listar();
	public List<Propietario> findByEmailAndPassword(String EmailPropietario, String ContraseñaPropietario);
}
