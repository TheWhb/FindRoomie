package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.SuscripcionXPropietario;
import pe.edu.upc.spring.repository.ISuscripcionXPropietarioRepository;
import pe.edu.upc.spring.service.ISuscripcionXPropietarioService;

@Service
public class SuscripcionXPropietarioServiceImpl implements ISuscripcionXPropietarioService {

	@Autowired
	private ISuscripcionXPropietarioRepository dSuscripcionXPropietarios;
	
	@Override
	@Transactional
	public boolean grabar(SuscripcionXPropietario suscripcionXPropietario) {
		SuscripcionXPropietario objSuscripcionXPropietarios = dSuscripcionXPropietarios.save(suscripcionXPropietario);
		if (objSuscripcionXPropietarios == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idSuscripcionXPropietario) {
		dSuscripcionXPropietarios.deleteById(idSuscripcionXPropietario);		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<SuscripcionXPropietario> listarId(int idSuscripcionXPropietario) {
		return dSuscripcionXPropietarios.findById(idSuscripcionXPropietario);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SuscripcionXPropietario> listar() {
		return dSuscripcionXPropietarios.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<SuscripcionXPropietario> buscarIdPropietario(int idPropietario){
		return dSuscripcionXPropietarios.buscarPropietario(idPropietario);
	}
}
