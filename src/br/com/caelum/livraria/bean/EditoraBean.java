package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.EditoraDao;
import br.com.caelum.livraria.modelo.Editora;
import br.com.caelum.livraria.tx.Transacional;
import br.com.caelum.livraria.util.RedirectView;

@Named
@ViewScoped
public class EditoraBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Editora editora = new Editora();
	private Integer editraId;
	private List<Editora> editoras;
	
	@Inject
	private EditoraDao dao;
	
	public Editora getEditora() {
		return editora;
	}
	
	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	
	public Integer getEditraId() {
		return editraId;
	}
	
	public void setEditraId(Integer editraId) {
		this.editraId = editraId;
	}
	
	public List<Editora> getEditoras(){
		if(this.editoras == null) {
			this.editoras = this.dao.listaTodos();
		}
		return this.editoras;
	}
	
	public void carregaPelaId(){
		Integer id = this.editora.getId();
		this.editora = this.dao.buscaPorId(id);
		
		if(this.editora.getId() == null) {
			this.editora = new Editora();
		}
	}
	
	@Transacional
	public RedirectView gravar() {
		
		if(this.editora.getId() == null) {
			this.dao.adiciona(editora);
		} else {
			this.dao.atualiza(editora);
		}
		
		return new RedirectView("editora");
	}
	
	@Transacional
	public void remover(Editora editora) {
		this.dao.remove(editora);
	}
	
	public void carregar(Editora editora) {
		this.editora = editora;
	}
}
