package com.example.demo.registro;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	HashMap<String, Object> datos;

	private final UsuarioRepository registroRepository;
	
	private final PasswordEncoder passwordEncoder;

	public UsuarioService(UsuarioRepository registroRepository) {
		this.registroRepository = registroRepository;
		this.passwordEncoder=new BCryptPasswordEncoder();
	}

	public List<Usuario> getRegistro() {

		return this.registroRepository.findAll();
		/*
		 * return List.of( new registro(1, "lsoria", "flores", "Lis", "Soria",
		 * "0503695264") );
		 */
	}

	public ResponseEntity<Object> newRegistro(Usuario Registro) {

		datos = new HashMap<>();
		
		String encoderPassword = this.passwordEncoder.encode(Registro.getContrasenia());

		Optional<Usuario> res = registroRepository.findRegistroByIdentificacion(Registro.getIdentificacion());
		Optional<Usuario> res2 = registroRepository.findRegistroByUsuario(Registro.getUsuario());
		
		if (res.isPresent()) {
			datos.put("error", true);
			datos.put("mensaje", "Ya existe el identificador");
			return new ResponseEntity<>(datos, HttpStatus.BAD_REQUEST);
	    }
		if (res2.isPresent()) {
			datos.put("error", true);
			datos.put("mensaje", "Ya existe el usuario");
			return new ResponseEntity<>(datos, HttpStatus.BAD_REQUEST);
		}
		if (Registro.getUsuario() == null || Registro.getContrasenia() == null
				|| Registro.getNombre() == null|| Registro.getApellido() == null
				|| Registro.getIdentificacion() == null|| Registro.getUsuario().isEmpty()
				|| Registro.getContrasenia().isEmpty()|| Registro.getNombre().isEmpty()
				|| Registro.getApellido().isEmpty()|| Registro.getIdentificacion().isEmpty()){
			datos.put("error", true);
			datos.put("mensaje", "No se permiten campos nulos o vacíos");
			return new ResponseEntity<>(datos, HttpStatus.BAD_REQUEST);
		}
		if (Registro.getUsuario().length() > 12 || Registro.getContrasenia().length() > 12
				|| Registro.getNombre().length() > 20 || Registro.getApellido().length() > 20
				|| Registro.getIdentificacion().length() > 13) {

			StringBuilder mensaje = new StringBuilder("Los siguientes campos exceden la longitud permitida: ");

			if (Registro.getUsuario().length() > 12) {
				mensaje.append("usuario (máximo 12 caracteres), ");
			}
			if (Registro.getContrasenia().length() > 12) {
				mensaje.append("contraseña (máximo 12 caracteres), ");
			}
			if (Registro.getNombre().length() > 15) {
				mensaje.append("nombre (máximo 15 caracteres), ");
			}
			if (Registro.getApellido().length() > 15) {
				mensaje.append("apellido (máximo 15 caracteres), ");
			}
			if (Registro.getIdentificacion().length() > 13) {
				mensaje.append("identificación (máximo 13 caracteres), ");
			}

			// Eliminar la coma y el espacio extra al final del mensaje
			mensaje.delete(mensaje.length() - 2, mensaje.length());

			datos.put("error", true);
			datos.put("mensaje", mensaje.toString());
			return new ResponseEntity<>(datos, HttpStatus.BAD_REQUEST);
		}

		if (!validarLetras(Registro.getNombre()) || !validarLetras(Registro.getApellido())) {
			datos.put("error", true);
			datos.put("mensaje", "El nombre y el apellido deben contener solo letras");
			return new ResponseEntity<>(datos, HttpStatus.BAD_REQUEST);
		}
		//crearNuevoRegistro
		Registro.setContrasenia(encoderPassword);
		registroRepository.save(Registro);
		datos.put("mensaje", "Se guardo correctamente");
		return new ResponseEntity<>(datos, HttpStatus.OK);
	}
	

	private boolean validarLetras(String cadena) {
		// Expresión regular para verificar que la cadena contenga solo letras
		String regex = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+$";
		return Pattern.matches(regex, cadena);
	}
	@Transactional
	public ResponseEntity<Object> actualizarRegistro(Usuario registro) {
	    datos = new HashMap<>();
	    
	    registroRepository.updateRegistro(registro.getIdentificacion(), registro.getContrasenia(), registro.getNombre(), registro.getApellido());

	    datos.put("mensaje", "Usuario actualizado correctamente");
	    return ResponseEntity.ok().body(datos);
	}

	public ResponseEntity<Object> obtenerUsuarioPorIdentificacion(String identificacion) {
		datos = new HashMap<>(); //aqui puse hashMap
		Optional<Usuario> res = registroRepository.findRegistroByIdentificacion(identificacion);
		if (!res.isPresent()) {
			datos.put("error", true);
			datos.put("mensaje", "No se encontró ningún usuario con la identificación proporcionada");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(datos);
		} else {
			datos.put("error", false);
			datos.put("mensaje", "Usuario encontrado");
			datos.put("usuario", res.get());
			return ResponseEntity.ok().body(datos);
		}
	}
	
	@Transactional
	public ResponseEntity<Object> deleteRegistro(String Id) {
	    datos = new HashMap<>();
	    Optional<Usuario> res = this.registroRepository.findRegistroByIdentificacion(Id);
	    if (!res.isPresent()) {
	        datos.put("error", true);
	        datos.put("mensaje", "No existe un usuario con ese identificador (CI/RUC/Pasaporte)");
	        return new ResponseEntity<>(
	                datos,
	                HttpStatus.ACCEPTED
	        );
	    } else {
	        this.registroRepository.deleteByIdentificacion(Id); 
	        datos.put("mensaje", "Usuario eliminado correctamente");
	        return new ResponseEntity<>(
	                datos,
	                HttpStatus.ACCEPTED
	        );
	    }
	}

}
