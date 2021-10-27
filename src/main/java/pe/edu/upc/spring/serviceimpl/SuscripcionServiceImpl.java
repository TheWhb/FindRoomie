package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Suscripcion;
import pe.edu.upc.spring.repository.ISuscripcionRepository;
import pe.edu.upc.spring.service.ISuscripcionService;

@Service
public class SuscripcionServiceImpl implements ISuscripcionService {

	@Autowired
	private ISuscripcionRepository dSuscripcion;
	
	@Override
	@Transactional
	public boolean grabar(Suscripcion suscripcion) {
		Suscripcion objSuscripcion = dSuscripcion.save(suscripcion);
		if (objSuscripcion == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idSuscripcion) {
		dSuscripcion.deleteById(idSuscripcion);		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Suscripcion> listarId(int idSuscripcion) {
		return dSuscripcion.findById(idSuscripcion);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Suscripcion> listar() {
		return dSuscripcion.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Suscripcion> buscarTipo(String TipoSuscripcion) {
		return dSuscripcion.buscarTipo(TipoSuscripcion);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Suscripcion> buscarCosto(int CostoSuscripcion) {
		return dSuscripcion.buscarCosto(CostoSuscripcion);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Suscripcion> buscarTipoCosto(String TipoSuscripcion, int CostoSuscripcion) {
		return dSuscripcion.buscarTipoCosto(TipoSuscripcion, CostoSuscripcion);
	}
}
