package br.com.agenda.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.agenda.dao.CadastroBD;
import br.com.agenda.dao.UsuarioBD;
import br.com.agenda.modelo.Usuario;
import br.com.agenda.tx.Transacional;

@Named
@ViewScoped
public class CadastroBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario = new Usuario();

	@Inject
	private CadastroBD dao;
		
	public Usuario getUsuario() {
		return usuario;
	}
	
	@Transacional
	public String salvarUsuario() {
		System.out.println("Entrou no metódo cadastrar");
		if (this.usuario.getId() == null) {
			dao.adiciona(this.usuario);
		} else {
			dao.atualiza(this.usuario);
		}
		return "login?faces-redirect=true";
	}
	

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
