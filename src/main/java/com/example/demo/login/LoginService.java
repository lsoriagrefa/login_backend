package com.example.demo.login;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.registro.registro;

@Service
public class LoginService{
	HashMap<String, Object> datos;

	private final LoginRepository loginRepository;

	@Autowired
	public LoginService(LoginRepository loginRepository) {
		this.loginRepository= loginRepository;
	}

	public ResponseEntity<Object> Autentificacion(registro Registro) {
		Optional<registro> res = loginRepository.findRegistroByUsuarioAndContrasenia(Registro.getUsuario(),
				Registro.getContrasenia());
		datos = new HashMap<>();

		if (!res.isPresent()) {
			datos.put("error", true);
			datos.put("mensaje", "Usuario o contraseña inválidos");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(datos);
		} else {
			datos.put("error", false);
			datos.put("mensaje", "Inicio de sesión exitoso");
			datos.put("usuario", res.get());
			return ResponseEntity.ok().body(datos);
		}
	}

}
