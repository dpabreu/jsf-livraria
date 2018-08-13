package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Editora;

public class EditoraDao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	EntityManager em;
	
	private DAO<Editora> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Editora>(em, Editora.class);
	}

	public void adiciona(Editora t) {
		dao.adiciona(t);
	}

	public void remove(Editora t) {
		dao.remove(t);
	}

	public void atualiza(Editora t) {
		dao.atualiza(t);
	}

	public List<Editora> listaTodos() {
		return dao.listaTodos();
	}

	public Editora buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}

	public int contaTodos() {
		return dao.contaTodos();
	}

	public List<Editora> listaTodosPaginada(int firstResult, int maxResults, String coluna, String valor) {
		return dao.listaTodosPaginada(firstResult, maxResults, coluna, valor);
	}
	
	

}
