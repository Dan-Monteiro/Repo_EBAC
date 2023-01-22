package br.com.dan.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.dan.domain.dao.IProdutoDao;
import br.com.dan.domain.dao.ProdutoDao;
import br.com.dan.domain.model.Produto;

public class ProdutoTest {

	private IProdutoDao produtoDao;
	
	public ProdutoTest() {
		this.produtoDao = new ProdutoDao();
	}
	
	@Test
	public void testCadastroProduto() {
		Produto produto = new Produto();
		produto.setNome("Café Árabe");
		produto.setDescricao("Autêntico café árabe");
		produto.setValor(40.00);
		
		Produto resultado = produtoDao.cadastrar(produto);
		
		assertNotNull(resultado);
		assertNotNull(produto.getId());
	}
}
