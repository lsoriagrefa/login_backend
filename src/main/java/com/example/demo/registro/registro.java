package com.example.demo.registro;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class registro {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique=true)
	private String usuario;
	
	private String contrasenia;
	
	private String confirmarContrasenia;
	
	private String nombre;
	
	@Column(unique=true)
	private String identificacion;
	
	public String getConfirmarContrasenia() {
		return confirmarContrasenia;
	}

	public void setConfirmarContrasenia(String confirmarContrasenia) {
		this.confirmarContrasenia = confirmarContrasenia;
	}

	private String apellido;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	
	public registro() {}

	public registro(Integer id, String usuario, String contrasenia, String confirmarContrasenia, String nombre,
			String apellido, String identificacion) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.confirmarContrasenia = confirmarContrasenia;
		this.nombre = nombre;
		this.apellido = apellido;
		this.identificacion = identificacion;
	}

	@Override
	public String toString() {
		return "registro [id=" + id + ", usuario=" + usuario + ", contrasenia=" + contrasenia
				+ ", confirmarContrasenia=" + confirmarContrasenia + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", identificacion=" + identificacion + "]";
	}
	
}
