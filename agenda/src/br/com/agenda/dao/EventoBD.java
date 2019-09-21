package br.com.agenda.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.TransientObjectException;

import br.com.agenda.modelo.Evento;
import br.com.agenda.modelo.Usuario;

public class EventoBD implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	
	private	DAO<Evento> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Evento>(this.em, Evento.class);
	}
	
	public List<Evento> listaEventosUser(Usuario usuarioLogado) {
		
		TypedQuery<Evento> query = em.createQuery(
				"select c from Evento c where c.usuario = :pUsuario", Evento.class);
		try {
		query.setParameter("pUsuario", usuarioLogado);
		
			List<Evento> result = query.getResultList();
			return result;
		}catch(TransientObjectException ex) {
			System.out.println("Erro: " + ex.toString());
			return null;
		}
	}

	public void atualiza(Evento t) {
		dao.atualiza(t);
	}

	public void adiciona(Evento t) {
		dao.adiciona(t);
	}

	public void remove(Evento t) {
		dao.remove(t);
	}
	
	

}
