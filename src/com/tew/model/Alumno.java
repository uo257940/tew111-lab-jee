package com.tew.model;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "alumno")
public class Alumno {
	private Long id;
	private String nombre;
	private String apellidos;
	private String iduser;
	private String email;

	public Alumno() {
	}
	public Alumno(Long id, String nombre, String apellidos, String iduser, String email) {
		this.id = id; this.nombre = nombre; this.apellidos = apellidos;
		this.iduser = iduser; this.email = email;
	}
	
	@XmlElement
	public String getNombre() {
		return nombre;
	}
	@XmlElement
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@XmlElement
	public String getApellidos() {
		return apellidos;
	}
	@XmlElement
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	@XmlElement
	public String getIduser() {
		return iduser;
	}
	@XmlElement
	public void setIduser(String iduser) {
		this.iduser = iduser;
	}
	@XmlElement
	public String getEmail() {
		return email;
	}
	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}
	
	@XmlElement
	public void setId(Long id) {
		this.id = id;
	}
	@XmlElement
	public Long getId() {
		return id;
	}
}
