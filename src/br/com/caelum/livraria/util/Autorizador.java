package br.com.caelum.livraria.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.caelum.livraria.modelo.Usuario;

public class Autorizador implements PhaseListener {

	private static final long serialVersionUID = 9039601385787424895L;

	@Override
	public void afterPhase(PhaseEvent evento) {
		FacesContext ctx = evento.getFacesContext();
		String nomePagina = ctx.getViewRoot().getViewId();
		
		if("/login.xhtml".equals(nomePagina)){
			return;
		}
		
		Usuario usuarioLogado = (Usuario) ctx.getExternalContext().getSessionMap().get("usuarioLogado");
		
		if(usuarioLogado != null){
			return;
		}
		
		NavigationHandler handler = ctx.getApplication().getNavigationHandler();
		handler.handleNavigation(ctx, null, "/login?faces-redirect=true");
		
		ctx.renderResponse();
	}

	@Override
	public void beforePhase(PhaseEvent evento) {
		System.out.println("FASE: " + evento.getPhaseId());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
