package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Vivienda;
import pe.edu.upc.spring.repository.IViviendaRepository;
import pe.edu.upc.spring.service.IViviendaService;

@Service
public class ViviendaServiceImpl implements IViviendaService {

	@Autowired
	private IViviendaRepository dVivienda;
	
	@Override
	@Transactional
	public boolean grabar(Vivienda vivienda) {
		Vivienda objVivienda = dVivienda.save(vivienda);
		if (objVivienda == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idVivienda) {
		dVivienda.deleteById(idVivienda);		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Vivienda> listarId(int idVivienda) {
		return dVivienda.findById(idVivienda);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Vivienda> buscarId(int idVivienda) {
		return dVivienda.findById(idVivienda);
	}


	@Override
	@Transactional(readOnly = true)
	public List<Vivienda> listar() {
		return dVivienda.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vivienda> buscarNombre(String NNombreVivienda) {
		return dVivienda.buscarNombre(NNombreVivienda);
	}
	
	
}
