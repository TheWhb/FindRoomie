package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Vivienda;

public interface IViviendaService {
	public boolean grabar(Vivienda vivienda);
	public void eliminar(int idVivienda);
	public Optional<Vivienda> listarId(int idVivienda);
	public Optional<Vivienda> buscarId(int idVivienda);
	public List<Vivienda> listar();
	public List<Vivienda> buscarNombre(String NNombreVivienda);
}
