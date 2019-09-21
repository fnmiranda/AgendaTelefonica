package br.com.agenda.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.agenda.dao.UsuarioBD;
import br.com.agenda.modelo.Usuario;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class UsuarioEditorBean implements Serializable {

	@Inject
	private FacesContext context;
	@Inject
	private UsuarioBD useuarioDB;

	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario pegarUser() {
		Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		return usuarioLogado;
	}

	public Usuario listarDadosDeUsuario() {
		usuario = useuarioDB.existeUsuario(pegarUser());

		System.out.println("Nome: " + usuario.getNome());
		System.out.println("Email: " + usuario.getEmail());
		System.out.println("Senha: " + usuario.getSenha());

		return usuario;
	}

}
