package com.example.demo.login;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.registro.Registro;

@Repository
public interface LoginRepository extends JpaRepository<Registro, Integer>{

	Optional<Registro>findRegistroByUsuario(String usuario);
}
