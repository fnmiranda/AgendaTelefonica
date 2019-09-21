package br.com.agenda.dao;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.agenda.modelo.Usuario;

public class CadastroBD implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	EntityManager em;
	private DAO<Usuario> dao;

	@PostConstruct
	void init() {
		this.dao = new DAO<Usuario>(this.em, Usuario.class);
	}

	public void adiciona(Usuario t) {
		dao.adiciona(t);
	}

	public void atualiza(Usuario t) {
		dao.atualiza(t);
	}
	


}
