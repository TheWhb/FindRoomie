package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Vivienda;

@Repository
public interface IViviendaRepository extends JpaRepository<Vivienda, Integer>{
	@Query("from Vivienda v where v.NNombreVivienda like %:NNombreVivienda%")
	List<Vivienda> buscarNombre(@Param("NNombreVivienda") String NNombreVivienda);
}
