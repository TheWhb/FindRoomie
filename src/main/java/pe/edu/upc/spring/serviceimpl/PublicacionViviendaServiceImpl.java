package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.PublicacionVivienda;
import pe.edu.upc.spring.repository.IPublicacionViviendaRepository;
import pe.edu.upc.spring.service.IPublicacionViviendaService;

@Service
public class PublicacionViviendaServiceImpl implements IPublicacionViviendaService {

	@Autowired
	private IPublicacionViviendaRepository dPublicacionPropietario;
	
	@Override
	@Transactional
	public boolean grabar(PublicacionVivienda publicacionVivienda) {
		PublicacionVivienda objPublicacionRoomie = dPublicacionPropietario.save(publicacionVivienda);
		if (objPublicacionRoomie == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idPublicacionVivienda) {
		dPublicacionPropietario.deleteById(idPublicacionVivienda);		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PublicacionVivienda> listarId(int idPublicacionVivienda) {
		return dPublicacionPropietario.findById(idPublicacionVivienda);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PublicacionVivienda> listar() {
		return dPublicacionPropietario.findAll();
	}


}
