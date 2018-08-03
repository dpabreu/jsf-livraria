package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.util.RedirectView;

@Named
@ViewScoped
public class AutorBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Autor autor = new Autor();
	private Integer autorId;
	
	@Inject
	private AutorDao dao;
	
	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Autor getAutor() {
		return autor;
	}
	
	public List<Autor> getAutores(){
		return this.dao.listaTodos();
	}
	
	public void carregaPelaId(){
		Integer id = this.autor.getId();
		this.autor = this.dao.buscaPorId(id);
		
		if(this.autor == null){
			this.autor = new Autor();
		}
	}
	
	public RedirectView gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if(this.autor.getId() == null){
			this.dao.adiciona(this.autor);
		}else{
			this.dao.atualiza(autor);
		}
		
		return new RedirectView("livro");
	}
	
	public void remover(Autor autor){
		System.out.println("Removendo autor " + this.autor.getNome());
		this.dao.remove(autor);
	}
	
	public void carregar(Autor autor){
		System.out.println("Carregando autor " + autor.getNome());
		this.autor = autor;
	}
}
