package com.example.demo.registro;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(path="api/v1")
public class registrocontroller {
	
	private final registroService RegistroService;
	
	@Autowired
	public registrocontroller(registroService RegistroService) {
		this.RegistroService=RegistroService;
	}
	
	@GetMapping(path="/registro")
	public List<registro>getRegistro(){
		return this.RegistroService.getRegistro();
	}
	
	@PostMapping(path="/registro")
	public ResponseEntity<Object> registrarUsuario(@RequestBody registro Registro) {
		return this.RegistroService.newRegistro(Registro);
	}

    @GetMapping(path="/registro/{Identificador}")
    public ResponseEntity<Object> obtenerUsuarioPorIdentificacion(@PathVariable("Identificador") String Id) {
    	return this.RegistroService.obtenerUsuarioPorIdentificacion(Id);
    }
    //actualizar
	@PutMapping(path="/registro")
	public ResponseEntity<Object> actualizarUsuario(@RequestBody registro Registro) {
		return this.RegistroService.actualizarRegistro(Registro);
	}
	//eliminar
	@DeleteMapping(path="/registro/{Identificador}")
	public ResponseEntity<Object> eliminar(@PathVariable("Identificador") String Id) {
		return this.RegistroService.deleteRegistro(Id);
	}
}
