package br.com.agenda.dao;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import br.com.agenda.dao.JPAUtil;
import br.com.agenda.modelo.Usuario;
import br.com.agenda.modelo.Contato;

public class PupulaBD implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		

		Usuario amado = geraUsuario("Admin", "admin@gmail.com", "cr3d302");
		em.persist(amado);

		Contato alquemista = geraContato("CREDE", "conteudo","(88)9 92777738",
				"01/01/1988", amado);

		em.persist(alquemista);


		em.getTransaction().commit();
		em.close();

	}

	private static Usuario geraUsuario(String nome, String email, String senha) {
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		return usuario;
	}

	private static Contato geraContato(String nome, String conteudo, String telefone, String data, Usuario usuario) {
		Contato anotacao = new Contato();
		anotacao.setNome(nome);
		anotacao.setConteudo(conteudo);
		anotacao.setTelefone(telefone);
		anotacao.setData(parseData(data));
		anotacao.setUsuario(usuario);
		return anotacao;
	}

	private static Calendar parseData(String data) {
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
