package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.PublicacionRoomie;
import pe.edu.upc.spring.repository.IPublicacionRoomieRepository;
import pe.edu.upc.spring.service.IPublicacionRoomieService;

@Service
public class PublicacionRoomieServiceImpl implements IPublicacionRoomieService {

	@Autowired
	private IPublicacionRoomieRepository dPublicacionRoomie;
	
	@Override
	@Transactional
	public boolean grabar(PublicacionRoomie publicacionRoomie) {
		PublicacionRoomie objPublicacionRoomie = dPublicacionRoomie.save(publicacionRoomie);
		if (objPublicacionRoomie == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idPublicacionRoomie) {
		dPublicacionRoomie.deleteById(idPublicacionRoomie);		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PublicacionRoomie> listarId(int idPublicacionRoomie) {
		return dPublicacionRoomie.findById(idPublicacionRoomie);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PublicacionRoomie> listar() {
		return dPublicacionRoomie.findAll();
	}


}
