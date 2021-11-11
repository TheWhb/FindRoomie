package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Propietario;
import pe.edu.upc.spring.repository.IPropietarioRepository;
import pe.edu.upc.spring.service.IPropietarioService;

@Service
public class PropietarioServiceImpl implements IPropietarioService {

	@Autowired
	private IPropietarioRepository dPropietario;
	
	@Override
	@Transactional
	public boolean grabar(Propietario propietario) {
		Propietario objPropietario = dPropietario.save(propietario);
		if (objPropietario == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idPropietario) {
		dPropietario.deleteById(idPropietario);		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Propietario> listarId(int idPropietario) {
		return dPropietario.findById(idPropietario);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Propietario> listar() {
		return dPropietario.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Propietario> findByEmailAndPassword(String EmailPropietario, String ContraseñaPropietario) {
		return dPropietario.findByEmailAndPassword(EmailPropietario, ContraseñaPropietario);
	}

}
