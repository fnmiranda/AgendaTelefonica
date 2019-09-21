package br.com.agenda.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.agenda.modelo.Usuario;

@SuppressWarnings("serial")
public class UsuarioBD implements Serializable{
	
	@Inject
	EntityManager em;
	
	public Usuario existeUsuario(Usuario usuario) {
		TypedQuery<Usuario> query = em.createQuery("select u from Usuario u "
			+ "where u.email = :pEmail and u.senha = :pSenha", Usuario.class);
//		System.out.println("Email: " + usuario.getEmail());
//		System.out.println("Senha: " + usuario.getSenha());
		
		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		try {
			Usuario result = query.getSingleResult();
			System.out.println("Usuario: " + result.getNome() + ", encontrado!");
			return result;
		}catch(NoResultException ex){
			System.out.println("Usuario não encontrado");
			return null;
		}
		
	}
	
}
