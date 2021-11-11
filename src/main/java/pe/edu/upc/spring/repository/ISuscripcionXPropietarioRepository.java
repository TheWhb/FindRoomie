package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.SuscripcionXPropietario;

@Repository
public interface ISuscripcionXPropietarioRepository extends JpaRepository<SuscripcionXPropietario, Integer>{
	@Query("from SuscripcionXPropietario sp where sp.propietarioSuscripcionXPropietario.idPropietario like :id")
	List<SuscripcionXPropietario> buscarPropietario(@Param("id") int idPropietario);
}
