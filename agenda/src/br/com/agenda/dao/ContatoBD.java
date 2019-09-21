package br.com.agenda.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.agenda.modelo.Contato;
import br.com.agenda.modelo.Usuario;

@SuppressWarnings("serial")
public class ContatoBD implements Serializable{

	@Inject
	EntityManager em;

	private DAO<Contato> dao;

	@PostConstruct
	void init() {
		this.dao = new DAO<Contato>(this.em, Contato.class);
	}

	public void adiciona(Contato t) {
		dao.adiciona(t);
	}

	public void remove(Contato t) {
		dao.remove(t);
	}

	public void atualiza(Contato t) {
		dao.atualiza(t);
	}


	public List<Contato> contatosDoUsuario(Usuario usuarioLogado) {
		TypedQuery<Contato> query = em.createQuery(
				"select c from Contato c " + "where c.usuario = :pUsuario order by data desc", Contato.class);
		query.setParameter("pUsuario", usuarioLogado);

		List<Contato> result = query.getResultList();
		return result;

	}

	public List<Contato> pesquisarContatosEspecificos(Usuario usuarioLogado, String busca) {
		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Contato> query = em.createQuery("select c from Contato c "
				+ "where c.usuario = :pUsuario and c.nome like :pBusca or c.data like :pBusca "
				+ "or c.telefone like :pBusca or c.conteudo like :pBusca order by data desc", Contato.class);
		query.setParameter("pUsuario", usuarioLogado);
		query.setParameter("pBusca", busca);

		List<Contato> result = query.getResultList();

		return result;
	}

}
