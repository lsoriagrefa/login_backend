package com.example.demo.registro;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	//@Query("SELECT * FROM registro p WHERE p.identificacion=?1")
	Optional<Usuario>findRegistroByIdentificacion(String identificacion);

	Optional<Usuario>findRegistroByUsuario(String usuario);
	//Buscarusuario
	
    void deleteByIdentificacion(String identificacion);
    
    @Modifying
    @Transactional
    @Query("UPDATE Registro r SET r.contrasenia = :contrasenia, r.nombre = :nombre, r.apellido = :apellido WHERE r.identificacion = :identificacion")
    void updateRegistro(@Param("identificacion") String identificacion, @Param("contrasenia") String contrasenia, @Param("nombre") String nombre, @Param("apellido") String apellido);
	

}
