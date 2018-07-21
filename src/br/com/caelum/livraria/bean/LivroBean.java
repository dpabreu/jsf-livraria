package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();
	private Integer autorID;

	public Integer getAutorID() {
		return autorID;
	}

	public void setAutorID(Integer autorID) {
		this.autorID = autorID;
	}

	public Livro getLivro() {
		return livro;
	}
	
	public List<Autor> getAutores(){
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public void carregaPelaId(){
		Integer id = this.livro.getId();
		this.livro = new DAO<Livro>(Livro.class).buscaPorId(id);
		
		if(this.livro == null){
			this.livro = new Livro();
		}
	}	
	
	public void gravarAutor(){
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorID);
		this.livro.adicionaAutor(autor);
	}
	
	public List<Autor> getAutoresDoLivro(){
		return this.livro.getAutores();
	}
	
	public List<Livro> getLivros(){
		return new DAO<Livro>(Livro.class).listaTodos();
	}
	
	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor."));
		}

		if(this.livro.getId() == null){
			new DAO<Livro>(Livro.class).adiciona(this.livro);
		}else{
			new DAO<Livro>(Livro.class).atualiza(this.livro);
		}
		
		this.livro = new Livro();
	}
	
	public void remover(Livro livro){
		System.out.println("Removendo livro" + livro.getTitulo());
		new DAO<Livro>(Livro.class).remove(livro);
	}
	
	public void carregar(Livro livro){
		System.out.println("Carregando livro" + livro.getTitulo());
		this.livro = livro;
		
	}
	
	public void removerAutorDoLivro(Autor autor){
		this.livro.removeAutor(autor);
	}
	
	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value){
		
		String valor = value.toString();
		if(!valor.startsWith("1")){
			throw new ValidatorException(new FacesMessage("Deveria começar com um."));
		}
	}
	
}
