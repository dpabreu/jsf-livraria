package br.com.caelum.livraria.modelo;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.livraria.dao.LivroDao;

public class LivroDataModel extends LazyDataModel<Livro> {

	private static final long serialVersionUID = 2948232237230849780L;
	
	@Inject
	LivroDao livroDao;
	
	@PostConstruct
	void init(){
		super.setRowCount(livroDao.contaTodos());
	}
	
	@Override
	public List<Livro> load(int inicio, int quantidade, String campoOrdenacao, 
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
		
		return livroDao.listaTodosPaginada(inicio, quantidade, campoFiltrado, valorDigitado);
	}

}
