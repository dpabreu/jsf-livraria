package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.log.Log;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.LivroDataModel;
import br.com.caelum.livraria.tx.Transacional;

@Named
@ViewScoped
public class LivroBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Livro livro = new Livro();
	private Integer autorID;
	private List<Livro> livros;
	private List<String> generos = Arrays.asList("Romance", "Drama", "Ação");
	
	@Inject 
	private LivroDataModel livroDataModel;

	@Inject
	private AutorDao autorDao;
	
	@Inject
	private LivroDao livroDao;
		
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
		return this.autorDao.listaTodos();
	}
	
	public void carregaPelaId(){
		Integer id = this.livro.getId();
		this.livro = this.livroDao.buscaPorId(id);
		
		if(this.livro == null){
			this.livro = new Livro();
		}
	}	
	
	public void gravarAutor(){
		Autor autor = this.autorDao.buscaPorId(this.autorID);
		this.livro.adicionaAutor(autor);
	}
	
	public List<Autor> getAutoresDoLivro(){
		return this.livro.getAutores();
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	@Log
	public List<Livro> getLivros(){
		if(this.livros == null){
			this.livros = this.livroDao.listaTodos();
		}
		
		return this.livros;
	}
	
	@Transacional
	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor."));
		}

		if(this.livro.getId() == null){
			this.livroDao.adiciona(this.livro);
			this.livros = this.livroDao.listaTodos();
		}else{
			this.livroDao.atualiza(this.livro);
		}
		
		this.livro = new Livro();
	}
	
	@Transacional
	public void remover(Livro livro){
		System.out.println("Removendo livro" + livro.getTitulo());
		this.livroDao.remove(livro);
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
