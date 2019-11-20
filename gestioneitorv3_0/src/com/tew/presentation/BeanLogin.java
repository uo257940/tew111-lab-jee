package com.tew.presentation;
import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import com.tew.business.LoginService;
import com.tew.infrastructure.Factories;
import com.tew.model.Alumno;
import com.tew.model.User;


@ManagedBean(name="login")
@SessionScoped
public class BeanLogin implements Serializable {
	private static final long serialVersionUID =25;
	private String name = "";
	private String password = "";

	public String verify() {
		// necesario para accede a msgs y a los mensajes en espa�ol e ingles de los ficheros
		// de propiedades
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");
		FacesMessage msg = null;
		LoginService login = Factories.services.createLoginService();
		User user = login.verify(name, password);
		if (user != null) {
			putUserInSession(user);
			return "success";
		}
		// si el usuario no se encuentra
		// se prepara el mensaje que saldra en la vista del cliente
		msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
				bundle.getString("login_form_result_error"), null);
		// se a�ade al element con id=�msg�
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "login";
	}
	private void putUserInSession(User user) {
		Map<String, Object> session =
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		session.put("LOGGEDIN_USER", user);
	}
}
