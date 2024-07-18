package com.example.demo.login;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.registro.Usuario;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(path="api/v1")
public class LoginController {
	
	private final LoginService loginService;
	
	@Autowired private Environment variable;

	public LoginController(LoginService loginService) {
		this.loginService=loginService;
	}
	
	//@CrossOrigin(origins = "http://172.17.24.14:8080")
    @PostMapping(path="/login")
    public ResponseEntity<Object> autenticarUsuario(@RequestHeader(value = "token") String tokenHeaders, @RequestBody Usuario registro) {
    	String token = variable.getProperty("miapp.apikey");
    	if(tokenHeaders.equals(token)) {
    		return loginService.Autentificacion(registro);
    	}else {
			HashMap<String,Object> datos= new HashMap<String, Object>();
			datos .put("error", false);
			datos.put("mensaje", "tOKEN INVALIDO");
			return ResponseEntity.ok().body(datos);
    	}
        
    }
}
