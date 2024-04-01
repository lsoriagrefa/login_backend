package com.example.demo.login;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.registro.registro;

@Service
public class LoginService {
	HashMap<String, Object> datos;

	private final LoginRepository loginRepository;

	private PasswordEncoder passwordEncoder;

	@Autowired
	public LoginService(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	public ResponseEntity<Object> Autentificacion(registro Registro) {
		Optional<registro> res = loginRepository.findRegistroByUsuario(Registro.getUsuario());
		datos = new HashMap<>();

		if (!res.isPresent()) {
			datos.put("error", true);
			datos.put("mensaje", "Usuario incorrecto");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(datos);
		} else {
			registro usuarioDB = res.get();
			if (passwordEncoder.matches(Registro.getContrasenia(), usuarioDB.getContrasenia())) {
				datos.put("error", false);
				datos.put("mensaje", "Inicio de sesión exitoso");
				return ResponseEntity.ok().body(datos);
			} else {
				datos.put("error", true);
				datos.put("mensaje", "Contraseña incorrecta");
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(datos);
			}
		}
	}

}
