package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.caelum.livraria.log.Log;
import br.com.caelum.livraria.modelo.Livro;

public class LivroDao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	
	private DAO<Livro> dao;
	
	@PostConstruct
	void init(){
		this.dao = new DAO<Livro>(em, Livro.class);
	}

	public int contaTodos() {
		return dao.contaTodos();
	}

	public List<Livro> listaTodosPaginada(int firstResult, int maxResults, String coluna, String valor) {
		return dao.listaTodosPaginada(firstResult, maxResults, coluna, valor);
	}

	public void adiciona(Livro t) {
		dao.adiciona(t);
	}

	public void remove(Livro t) {
		dao.remove(t);
	}

	public void atualiza(Livro t) {
		dao.atualiza(t);
	}
	
	@Log
	public List<Livro> listaTodos() {
		return dao.listaTodos();
	}

	public Livro buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}

	public List<Livro> buscaLivros(String titulo, String genero, Integer editoraId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Livro> query = builder.createQuery(Livro.class);
		Root<Livro> root = query.from(Livro.class);
		
		Path<String> tituloPath = root.get("titulo");
		Path<String> generoPath = root.get("genero");
		Path<Integer> editoraPath = root.get("editora").get("id");
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(!"".equals(titulo)) {
			Predicate tituloPredicate = builder.like(tituloPath, "%" + titulo + "%");
			predicates.add(tituloPredicate);
		}
		
		if(!"".equals(genero)) {
			Predicate generoPredicate = builder.equal(generoPath, genero);
			predicates.add(generoPredicate);
		}

		if( editoraId > 0) {
			Predicate editoraPredicate = builder.equal(editoraPath, editoraId);
			predicates.add(editoraPredicate);
		}
		
		query.where((Predicate[]) predicates.toArray(new Predicate[0]));
		
		return em.createQuery(query).getResultList();
	}
}
