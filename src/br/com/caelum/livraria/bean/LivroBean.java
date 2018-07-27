package br.com.caelum.livraria.bean;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.LivroDataModel;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();
	private Integer autorID;
	private List<Livro> livros;
	private LivroDataModel livroDataModel = new LivroDataModel();
	private List<String> generos = Arrays.asList("Romance", "Drama", "Ação");

	public List<String> getGeneros() {
	    return generos;
	}
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
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public List<Livro> getLivros(){
		DAO<Livro> dao = new DAO<Livro>(Livro.class);
		
		if(this.livros == null){
			this.livros = dao.listaTodos();
		}
		
		return this.livros;
	}
	
	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor."));
		}

		DAO<Livro> dao = new DAO<Livro>(Livro.class);
		if(this.livro.getId() == null){
			dao.adiciona(this.livro);
			this.livros = dao.listaTodos();
		}else{
			dao.atualiza(this.livro);
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
	
	public boolean precoEhMenor(Object valorDaColuna, Object filtro, Locale locale){
		
		String textoDigitado = (filtro == null) ? null : filtro.toString().trim();
		
		if (textoDigitado==null || textoDigitado.equals("")){
			return true;
		}
		
		if (valorDaColuna == null){
			return false;
		}
		
		try {
			Double precoDigitado = Double.valueOf(textoDigitado);
			Double precoColuna = (Double) valorDaColuna;
			
			return precoColuna.compareTo(precoDigitado) < 0;
		} catch (NumberFormatException e) {
			
			return false;
		}
	}

	public LivroDataModel getLivroDataModel() {
		return livroDataModel;
	}

	public void setLivroDataModel(LivroDataModel livroDataModel) {
		this.livroDataModel = livroDataModel;
	}
	
}
