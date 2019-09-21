package br.com.agenda.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.agenda.dao.ContatoBD;
import br.com.agenda.modelo.Contato;
import br.com.agenda.modelo.Usuario;
import br.com.agenda.tx.Transacional;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class PrincipalBean implements Serializable{
	
	

	@Inject
	private ContatoBD dao; 
	
	@Inject
	private FacesContext context; 
	
	private Contato contato = new Contato();
	private Usuario user = new Usuario();
	
	private List<Contato> contatos;
	private List<Contato> contatosFiltrados;


	
	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public List<Contato> getContatosFiltrados() {
		return contatosFiltrados;
	}

	public void setContatosFiltrados(List<Contato> contatosFiltrados) {
		this.contatosFiltrados = contatosFiltrados;
	}
	
	//Metodos de ação
	
	public void relacionaUuarioEcontato() {
		Usuario usuarioLogado = pegarUser(); 
		getContato().setUsuario(usuarioLogado);
	}
	
	@Transacional
	public void salvarContatos() {
		
		relacionaUuarioEcontato();
		if (this.getContato().getId() == null) {
			dao.adiciona(this.getContato());
			this.setContatos(dao.contatosDoUsuario(pegarUser()));
		} else {
			dao.atualiza(this.getContato());
		}

		this.setContato(new Contato());
	}

	
	public void carregarContato(Contato contato) {
		this.setContato(contato);
	}
	
	@Transacional
	public void removerContato(Contato contato) {
		dao.remove(contato);
		this.setContatos(dao.contatosDoUsuario(pegarUser()));
	}

	public Usuario pegarUser() {
		Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		return usuarioLogado;
	}
	
	public List<Contato> getContatos() {
		List<Contato> contatosDoUsuario = dao.contatosDoUsuario(pegarUser());
		
		if(contatos == null) {
			if(contatosDoUsuario != null) {
				setContatos(contatosDoUsuario);
			}
		}
		
		return contatos;
	}
	
}
