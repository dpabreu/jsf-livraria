package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;
import br.com.caelum.livraria.util.RedirectView;

@Named
@ViewScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario(){
		return usuario;
	}
	
	public RedirectView efetuarLogin(){
		FacesContext ctx = FacesContext.getCurrentInstance();
		boolean existe = new UsuarioDao().existe(this.usuario);
		
		if(existe){
			ctx.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return new RedirectView("livro");
		}
		
		ctx.getExternalContext().getFlash().setKeepMessages(true);
		ctx.addMessage(null, new FacesMessage("Usuário não existe"));
		return new RedirectView("livro");
	}
	
	public RedirectView deslogar(){
		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.getExternalContext().getSessionMap().remove("usuarioLogado");
		return new RedirectView("login");
	}
}
