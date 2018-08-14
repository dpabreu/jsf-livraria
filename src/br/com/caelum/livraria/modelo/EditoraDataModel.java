package br.com.caelum.livraria.modelo;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.livraria.dao.EditoraDao;

public class EditoraDataModel extends LazyDataModel<Editora> {

	private static final long serialVersionUID = 2948232237230849780L;
	
	@Inject
	EditoraDao editoraDao;
	
	@PostConstruct
	void init(){
		super.setRowCount(editoraDao.contaTodos());
	}
	
	@Override
	public List<Editora> load(int inicio, int quantidade, String campoOrdenacao, 
				SortOrder sentidoOrdenacao, Map<String, Object> filtros){
		
		Set<String> keySet = filtros.keySet();
		String valorDigitado = null;
		String campoFiltrado = null;
		
		for (String key : keySet) {
			if(filtros.containsKey(key)) {
				valorDigitado = (String) filtros.get(key);
				campoFiltrado = key;
			}
		}
		
		return editoraDao.listaTodosPaginada(inicio, quantidade, campoFiltrado, valorDigitado);
	}

}
