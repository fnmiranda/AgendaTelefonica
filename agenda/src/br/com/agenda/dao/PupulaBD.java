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

		Usuario assis = geraUsuario("Nico", "nico@gmail.com", "12345");
		em.persist(assis);

		Usuario amado = geraUsuario("Italo", "italo@gmail.com", "qwe123");
		em.persist(amado);

		
		
		Contato casmurro = geraContato("Minecraft","conteudo ","(88)9 92777738",
				"10/01/1899", assis);
		Contato memorias = geraContato("Casa","conteudo","(88)9 92777738", "01/01/1881", assis);
		Contato quincas = geraContato("Quincas Borba","conteudo","(88)9 92777738",
				"01/01/1891", assis);

		em.persist(casmurro);
		em.persist(memorias);
		em.persist(quincas);

		Contato alquemista = geraContato("alquimista", "conteudo","(88)9 92777738",
				"01/01/1988", amado);
		Contato brida = geraContato("número", "Brida","(88)9 92777738", "01/01/1990",
				amado);
		Contato valkirias = geraContato("ligações", "As Valkirias","(88)9 92777738",
				"01/01/1992", amado);
		Contato maao = geraContato("Comida", "O Diario de um Mago","(88)9 92777738",
				"01/01/1987", amado);

		em.persist(alquemista);
		em.persist(brida);
		em.persist(valkirias);
		em.persist(maao);


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
