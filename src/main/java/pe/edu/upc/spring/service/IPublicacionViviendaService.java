package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.PublicacionVivienda;

public interface IPublicacionViviendaService {
	public boolean grabar(PublicacionVivienda  publicacionVivienda);
	public void eliminar(int idPublicacionVivienda);
	public Optional<PublicacionVivienda > listarId(int idPublicacionVivienda);
	public List<PublicacionVivienda > listar();
}
