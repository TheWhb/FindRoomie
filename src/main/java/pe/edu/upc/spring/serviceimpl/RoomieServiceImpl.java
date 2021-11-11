package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Roomie;
import pe.edu.upc.spring.repository.IRoomieRepository;
import pe.edu.upc.spring.service.IRoomieService;

@Service
public class RoomieServiceImpl implements IRoomieService {

	@Autowired
	private IRoomieRepository dRoomie;
	
	@Override
	@Transactional
	public boolean grabar(Roomie roomie) {
		Roomie objRoomie = dRoomie.save(roomie);
		if (objRoomie == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idRoomie) {
		dRoomie.deleteById(idRoomie);		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Roomie> listarId(int idRoomie) {
		return dRoomie.findById(idRoomie);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Roomie> listar() {
		return dRoomie.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Roomie> findByEmailAndPassword(String EmailRoomie, String ContraseñaRoomie) {
		return dRoomie.findByEmailAndPassword(EmailRoomie, ContraseñaRoomie);
	}


}
