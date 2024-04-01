package com.example.demo.login;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.registro.registro;

@Repository
public interface LoginRepository extends JpaRepository<registro, Integer>{

	Optional<registro>findRegistroByUsuario(String usuario);
}
