package com.example.demo.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.registro.registro;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(path="api/v1")
public class LoginController {
	
	private final LoginService loginService;
	
	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService=loginService;
	}
	
	//@CrossOrigin(origins = "http://172.17.24.14:8080")
    @PostMapping(path="/login")
    public ResponseEntity<Object> autenticarUsuario(@RequestBody registro Registro) {
        return loginService.Autentificacion(Registro);
    }
}
