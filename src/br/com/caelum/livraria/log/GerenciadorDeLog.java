package br.com.caelum.livraria.log;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Log
@Interceptor
public class GerenciadorDeLog implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@AroundInvoke
	public Object executaLog(InvocationContext contexto) throws Exception{
		
		long antes = System.currentTimeMillis();

		Object resultado = contexto.proceed();

		long depois = System.currentTimeMillis();
		
		long tempoGasto = depois - antes; 
		
		System.out.println("Tempo gasto do método: " + contexto.getMethod().getName() + " " + tempoGasto );
		
		return resultado;
	}
	
}
