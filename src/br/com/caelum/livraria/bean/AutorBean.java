package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();
//	private Integer autorId;
//	
//	public Integer getAutorId() {
//		return autorId;
//	}
//
//	public void setAutorId(Integer autorId) {
//		this.autorId = autorId;
//	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Autor getAutor() {
		return autor;
	}
	
	public List<Autor> getAutores(){
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public void carregaPelaId(){
		Integer id = this.autor.getId();
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(id);
		
		if(this.autor == null){
			this.autor = new Autor();
		}
	}
	
	public RedirectView gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if(this.autor.getId() == null){
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		}else{
			new DAO<Autor>(Autor.class).atualiza(autor);
		}
		
		return new RedirectView("livro");
	}
	
	public void remover(Autor autor){
		System.out.println("Removendo autor " + this.autor.getNome());
		new DAO<Autor>(Autor.class).remove(autor);
	}
	
	public void carregar(Autor autor){
		System.out.println("Carregando autor " + autor.getNome());
		this.autor = autor;
	}
}
