package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Suscripcion;

@Repository
public interface ISuscripcionRepository extends JpaRepository<Suscripcion, Integer>{
	@Query("from Suscripcion s where s.TipoSuscripcion like %:TipoSuscripcion%")
	List<Suscripcion> buscarTipo(@Param("TipoSuscripcion") String TipoSuscripcion);
	
	@Query("from Suscripcion s where s.CostoSuscripcion like %:CostoSuscripcion%")
	List<Suscripcion> buscarCosto(@Param("CostoSuscripcion") int CostoSuscripcion);
	
	@Query("from Suscripcion s where s.TipoSuscripcion like %:TipoSuscripcion% and s.CostoSuscripcion like %:CostoSuscripcion%")
	List<Suscripcion> buscarTipoCosto(@Param("TipoSuscripcion") String TipoSuscripcion, @Param("CostoSuscripcion") int CostoSuscripcion);
}
