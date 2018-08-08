package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.log.Log;
import br.com.caelum.livraria.modelo.Venda;

public class VendaDao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	
	private DAO<Venda> dao;
	
	@PostConstruct
	void init(){
		this.dao = new DAO<Venda>(em, Venda.class);
	}
	
	public void adiciona(Venda t) {
		dao.adiciona(t);
	}

	public void remove(Venda t) {
		dao.remove(t);
	}

	public void atualiza(Venda t) {
		dao.atualiza(t);
	}

	public Venda buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}

	@Log
	public List<Venda> getVendas(){
		return dao.listaTodos();
	}	
}
