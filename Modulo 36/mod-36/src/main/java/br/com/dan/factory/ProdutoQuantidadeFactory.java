package br.com.dan.factory;

import br.com.dan.domain.Produto;
import br.com.dan.domain.ProdutoQuantidade;
import br.com.dan.domain.Venda;

public class ProdutoQuantidadeFactory {

	public static ProdutoQuantidade build(Venda venda, Produto produto, Integer quantidade) {
		ProdutoQuantidade prodQ = new ProdutoQuantidade();
		prodQ.setVenda(venda);
		prodQ.setProduto(produto);
		prodQ.adicionar(quantidade);
		return prodQ;
	}
}