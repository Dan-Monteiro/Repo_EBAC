package br.com.dan;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import br.com.dan.Exceptions.DAOException;
import br.com.dan.Exceptions.MaisDeUmRegistroException;
import br.com.dan.Exceptions.TableException;
import br.com.dan.Exceptions.TipoChaveNaoEncontradaException;
import br.com.dan.dao.IProdutoDAO;
import br.com.dan.dao.ProdutoDAO;
import br.com.dan.domain.Produto;

public class ProdutoDAOTest {

	private IProdutoDAO produtoDAO;
	
	public ProdutoDAOTest() {
		this.produtoDAO = new ProdutoDAO();
	}
	
	@After
	public void end() throws DAOException {
		Collection<Produto> list = produtoDAO.buscarTodos();
		list.forEach(cli -> {
			try {
				produtoDAO.excluir(cli);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	@Test
	public void pesquisar() throws MaisDeUmRegistroException, TableException, DAOException, TipoChaveNaoEncontradaException {
		Produto produto = criarProduto("A1");
		Assert.assertNotNull(produto);
		Produto produtoDB = this.produtoDAO.consultar(produto.getId());
		Assert.assertNotNull(produtoDB);
	}
	
	@Test
	public void salvar() throws TipoChaveNaoEncontradaException, DAOException {
		Produto produto = criarProduto("A2");
		Assert.assertNotNull(produto);
	}
	
	@Test
	public void excluir() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {
		Produto produto = criarProduto("A3");
		Assert.assertNotNull(produto);
		this.produtoDAO.excluir(produto);
		Produto produtoBD = this.produtoDAO.consultar(produto.getId());
		assertNull(produtoBD);
	}
	
	@Test
	public void alterarProduto() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
		Produto produto = criarProduto("A4");
		produto.setNome("Produto 2");
		produtoDAO.alterar(produto);
		Produto produtoBD = this.produtoDAO.consultar(produto.getId());
		assertNotNull(produtoBD);
		Assert.assertEquals("Produto 2", produtoBD.getNome());
	}
	
	@Test
	public void buscarTodos() throws DAOException, TipoChaveNaoEncontradaException {
		criarProduto("A5");
		criarProduto("A6");
		Collection<Produto> list = produtoDAO.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 2);
		
		for (Produto prod : list) {
			this.produtoDAO.excluir(prod);
		}
		
		list = produtoDAO.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 0);
		
	}
	
	private Produto criarProduto(String codigo) throws TipoChaveNaoEncontradaException, DAOException {
		Produto produto = new Produto();
		produto.setCodigo(codigo);
		produto.setDescricao("Produto 1");
		produto.setNome("Produto 1");
		produto.setValor(BigDecimal.TEN);
		produtoDAO.cadastrar(produto);
		return produto;
	}
}