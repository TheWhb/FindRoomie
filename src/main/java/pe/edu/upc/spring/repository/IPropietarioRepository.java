package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Propietario;

@Repository
public interface IPropietarioRepository extends JpaRepository<Propietario, Integer>{
	@Query("from Propietario p where p.EmailPropietario = :Email and p.ContraseñaPropietario = :Contraseña")
	List<Propietario> findByEmailAndPassword(@Param("Email")String EmailPropietario, @Param("Contraseña")String ContraseñaPropietario);
}
