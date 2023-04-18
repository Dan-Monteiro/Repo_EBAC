package br.com.dan;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Random;

import br.com.dan.dao.ClientDAOAlt;
import br.com.dan.generic.GenericDAO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.dan.Exceptions.DAOException;
import br.com.dan.Exceptions.MaisDeUmRegistroException;
import br.com.dan.Exceptions.TableException;
import br.com.dan.Exceptions.TipoChaveNaoEncontradaException;
import br.com.dan.dao.ClienteDAO;
import br.com.dan.dao.IClienteDAO;
import br.com.dan.domain.Cliente;

public class ClienteDAOTest {
	
	private IClienteDAO clienteDAO;
	
	private Random rd;
	
	public ClienteDAOTest() {
		this.clienteDAO = new ClientDAOAlt();
		rd = new Random();
	}
	
	@After
	public void end() throws DAOException {
		Collection<Cliente> list = clienteDAO.buscarTodos();
		list.forEach(cli -> {
			try {
				clienteDAO.excluir(cli);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	/**
	 * Simulando verificação de execução em banco de teste e.g: Banco Postgres Alternativo podendo ser alterado
	 * por exemplo para um db SQLITE
	 * */
	@Before
	public void verificaExecucaoBancoTeste() {
		Assert.assertEquals(clienteDAO.getScopeDB(), GenericDAO.TEST);
	}
	
	@Test
	public void pesquisarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
		Cliente cliente = criarCliente();
		clienteDAO.cadastrar(cliente);
		
		Cliente clienteConsultado = clienteDAO.consultar(cliente.getId());
		Assert.assertNotNull(clienteConsultado);
		
	}

	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		Cliente cliente = criarCliente();
		Cliente retorno = clienteDAO.cadastrar(cliente);
		Assert.assertNotNull(retorno);
		
		Cliente clienteConsultado = clienteDAO.consultar(retorno.getId());
		Assert.assertNotNull(clienteConsultado);
		
		clienteDAO.excluir(cliente);
		
		Cliente clienteConsultado1 = clienteDAO.consultar(retorno.getId());
		Assert.assertNull(clienteConsultado1);
	}
	
	@Test
	public void excluirCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		Cliente cliente = criarCliente();
		Cliente retorno = clienteDAO.cadastrar(cliente);
		Assert.assertNotNull(retorno);
		
		Cliente clienteConsultado = clienteDAO.consultar(cliente.getId());
		Assert.assertNotNull(clienteConsultado);
		
		clienteDAO.excluir(cliente);
		clienteConsultado = clienteDAO.consultar(cliente.getId());
		Assert.assertNull(clienteConsultado);
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		Cliente cliente = criarCliente();
		Cliente retorno = clienteDAO.cadastrar(cliente);
		Assert.assertNotNull(retorno);
		
		Cliente clienteConsultado = clienteDAO.consultar(cliente.getId());
		Assert.assertNotNull(clienteConsultado);
		
		clienteConsultado.setNome("Dan");
		clienteDAO.alterar(clienteConsultado);
		
		Cliente clienteAlterado = clienteDAO.consultar(clienteConsultado.getId());
		Assert.assertNotNull(clienteAlterado);
		Assert.assertEquals("Dan", clienteAlterado.getNome());
		
		clienteDAO.excluir(cliente);
		clienteConsultado = clienteDAO.consultar(clienteAlterado.getId());
		Assert.assertNull(clienteConsultado);
	}
	
	@Test
	public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException {
		Cliente cliente = criarCliente();
		Cliente retorno = clienteDAO.cadastrar(cliente);
		Assert.assertNotNull(retorno);
		
		Cliente cliente1 = criarCliente();
		Cliente retorno1 = clienteDAO.cadastrar(cliente1);
		Assert.assertNotNull(retorno1);
		
		Collection<Cliente> list = clienteDAO.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 2);
		
		list.forEach(cli -> {
			try {
				clienteDAO.excluir(cli);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		Collection<Cliente> list1 = clienteDAO.buscarTodos();
		assertTrue(list1 != null);
		assertTrue(list1.size() == 0);
	}
	
	private Cliente criarCliente() {
		Cliente cliente = new Cliente();
		cliente.setCpf(rd.nextLong());
		cliente.setNome("Daniel");
		cliente.setCidade("Recife");
		cliente.setEnd("End");
		cliente.setEstado("PE");
		cliente.setNumero(10);
		cliente.setTel(1199999999L);
		return cliente;
	}

}