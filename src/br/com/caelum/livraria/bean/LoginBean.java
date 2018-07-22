package br.com.caelum.livraria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario(){
		return usuario;
	}
	
	public RedirectView efetuarLogin(){
		
		boolean existe = new UsuarioDao().existe(usuario);
		
		if(existe){
			return new RedirectView("livro");
		}
		
		return null;
	}
}
