package com.example.demo.registro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(path="api/v1")
public class RegistroController {
	
	private final RegistroService registroService;
	
	@Autowired private Environment variable;
	@Autowired
	public RegistroController(RegistroService registroService) {
		this.registroService=registroService;
	}
	
	@GetMapping(path="/registro")
	public List<Registro>getRegistro(){
		return this.registroService.getRegistro();
	}
	
	@PostMapping(path="/registro")
	public ResponseEntity<Object> registrarUsuario(@RequestHeader(value = "token") String tokenHeaders, @RequestBody Registro Registro) {
		String token = variable.getProperty("miapp.apikey");
    	if(tokenHeaders.equals(token)) {
    		return this.registroService.newRegistro(Registro);
    	}else {
			HashMap<String,Object> datos= new HashMap<String, Object>();
			datos .put("error", false);
			datos.put("mensaje", "TOKEN INVALIDO");
			return ResponseEntity.ok().body(datos);
    	}
	}

    @GetMapping(path="/registro/{Identificador}")
    public ResponseEntity<Object> obtenerUsuarioPorIdentificacion(@PathVariable("Identificador") String Id) {
    	return this.registroService.obtenerUsuarioPorIdentificacion(Id);
    }
    //actualizar
	@PutMapping(path="/registro")
	public ResponseEntity<Object> actualizarUsuario(@RequestBody Registro Registro) {
		return this.registroService.actualizarRegistro(Registro);
	}
	//eliminar
	@DeleteMapping(path="/registro/{Identificador}")
	public ResponseEntity<Object> eliminar(@PathVariable("Identificador") String Id) {
		return this.registroService.deleteRegistro(Id);
	}
}
