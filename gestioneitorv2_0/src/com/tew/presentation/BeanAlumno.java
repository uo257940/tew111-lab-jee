package com.tew.presentation;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import com.tew.model.Alumno;
@ManagedBean(name="alumno")
@SessionScoped
public class BeanAlumno extends Alumno implements Serializable {
	private static final long serialVersionUID = 55556L;
	//uso de inyección de dependencia
	@ManagedProperty(value="#{alumno}")
	private BeanAlumno alumno;
	public BeanAlumno getAlumno() { return alumno; }
	public void setAlumno(BeanAlumno alumno) {this.alumno = alumno;}

	public BeanAlumno() {
		iniciaAlumno(null);
	}
	//Este método es necesario para copiar el alumno a editar cuando
	//se pincha el enlace Editar en la vista listado.xhtml. Podría sustituirse
	//por un método editar en BeanAlumnos.
	public void setAlumno(Alumno alumno) {
		setId(alumno.getId());
		setIduser(alumno.getIduser());
		setNombre(alumno.getNombre());
		setApellidos(alumno.getApellidos());
		setEmail(alumno.getEmail());
	}
	//Iniciamos los datos del alumno con los valores por defecto
	//extraídos del archivo de propiedades correspondiente
	public void iniciaAlumno(ActionEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle =
				facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		setId(null);
		setIduser(bundle.getString("valorDefectoUserId"));
		setNombre(bundle.getString("valorDefectoNombre"));
		setApellidos(bundle.getString("valorDefectoApellidos"));
		setEmail(bundle.getString("valorDefectoCorreo"));
	}

	//Se inicia correctamente el MBean inyectado si JSF lo hubiera crea
	//y en caso contrario se crea. (hay que tener en cuenta que es un Bean de sesión)
	//Se usa @PostConstruct, ya que en el contructor no se sabe todavía si el Managed Bean
	//ya estaba construido y en @PostConstruct SI.
	@PostConstruct
	public void init() {
		System.out.println("BeanAlumnos - PostConstruct");
		//Buscamos el alumno en la sesión. Esto es un patrón factoría claramente.
		alumno = (BeanAlumno) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("alumno"));
		//si no existe lo creamos e inicializamos
		if (alumno == null) {
			System.out.println("BeanAlumnos - No existia");
			alumno = new BeanAlumno();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "alumno", alumno);
		}
	}
	@PreDestroy
	public void end() {
		System.out.println("BeanAlumnos - PreDestroy");
	}
}