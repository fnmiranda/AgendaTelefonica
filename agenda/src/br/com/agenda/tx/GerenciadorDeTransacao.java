package br.com.agenda.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@SuppressWarnings("serial")
@Transacional
@Interceptor
public class GerenciadorDeTransacao implements Serializable{
	
	@Inject
	EntityManager manager;
	
	@AroundInvoke
	public Object executarTX(InvocationContext contexto) throws Exception {
		manager.getTransaction().begin();
		
		//chamada dos DAOs
		Object proceed = contexto.proceed();
		
		manager.getTransaction().commit();
		
		return proceed;
	}
}
