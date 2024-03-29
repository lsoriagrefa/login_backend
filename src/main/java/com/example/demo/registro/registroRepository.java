package com.example.demo.registro;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface registroRepository extends JpaRepository<registro, Integer> {
	
	//@Query("SELECT * FROM registro p WHERE p.identificacion=?1")
	Optional<registro>findRegistroByIdentificacion(String identificacion);

	Optional<registro>findRegistroByUsuario(String usuario);
	//Buscarusuario
	
	

}
