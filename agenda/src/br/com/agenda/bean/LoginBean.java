package br.com.agenda.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.agenda.dao.UsuarioBD;
import br.com.agenda.modelo.Usuario;

@Named
@ViewScoped
public class LoginBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioBD userDao;

	@Inject
	private FacesContext context;
	
	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String efetuarLogin() {
		
		Usuario usuarioExistente = userDao.existeUsuario(usuario);
		
		if(usuarioExistente != null) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", usuarioExistente);
			return "principal?faces-redirect=true";
		}
		
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usuário não encontrado!"));
		
		return "login?faces-redirect=true";
	}
	
	public String efetuarLogout() {
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}
	
	public String cadastrarNovoUsuario() {
		return "/cadastroDeUsuario?faces-redirect=true";
	}
}
