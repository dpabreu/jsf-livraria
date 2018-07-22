package br.com.caelum.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.caelum.livraria.modelo.Usuario;

public class UsuarioDao {

	public boolean existe(Usuario usuario) {

		EntityManager em = new JPAUtil().getEntityManager();
		
		Query query = em.createQuery("select u from Usuario u where u.email = :pEmail and u.senha = :pSenha", Usuario.class);
		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		
		try {
			@SuppressWarnings("unused")
			Usuario user = (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return false;
		}
		
		return true;
	}

}
