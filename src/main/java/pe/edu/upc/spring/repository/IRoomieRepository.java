package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Roomie;

@Repository
public interface IRoomieRepository extends JpaRepository<Roomie, Integer>{
	@Query("from Roomie r where r.EmailRoomie = :Email and r.ContraseñaRoomie = :Contraseña")
	List<Roomie> findByEmailAndPassword(@Param("Email")String EmailRoomie, @Param("Contraseña")String ContraseñaRoomie);
}
