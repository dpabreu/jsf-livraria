package br.com.caelum.livraria.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transacional
@Interceptor
public class GerenciadorDeTransacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	EntityManager manager;
	
	@AroundInvoke
	public Object executarTX(InvocationContext contexto) throws Exception{
		
		System.out.println("Inciando tx");
		manager.getTransaction().begin();
		
		Object resultado = contexto.proceed();

		System.out.println("Fechando tx");
		manager.getTransaction().commit();
		
		return resultado;
		
	}
}
