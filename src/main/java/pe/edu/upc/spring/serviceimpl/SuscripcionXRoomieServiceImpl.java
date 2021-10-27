package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.SuscripcionXRoomie;
import pe.edu.upc.spring.repository.ISuscripcionXRoomieRepository;
import pe.edu.upc.spring.service.ISuscripcionXRoomieService;

@Service
public class SuscripcionXRoomieServiceImpl implements ISuscripcionXRoomieService {

	@Autowired
	private ISuscripcionXRoomieRepository dSuscripcionXRoomie;
	
	@Override
	@Transactional
	public boolean grabar(SuscripcionXRoomie suscripcionXRoomie) {
		SuscripcionXRoomie objSuscripcionXRoomie = dSuscripcionXRoomie.save(suscripcionXRoomie);
		if (objSuscripcionXRoomie == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idSuscripcionXRoomie) {
		dSuscripcionXRoomie.deleteById(idSuscripcionXRoomie);		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<SuscripcionXRoomie> listarId(int idSuscripcionXRoomie) {
		return dSuscripcionXRoomie.findById(idSuscripcionXRoomie);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SuscripcionXRoomie> listar() {
		return dSuscripcionXRoomie.findAll();
	}


}
