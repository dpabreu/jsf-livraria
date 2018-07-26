package br.com.caelum.livraria.modelo;

public class Venda {
	
	private Livro livro;
	private Integer quantidade;
	
	public Venda(Livro livro, Integer quantidade) {
		this.setLivro(livro);
		this.setQuantidade(quantidade);
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	
}
