import domain.dao.ClienteDAO;
import domain.dao.IClienteDAO;
import domain.exceptions.DAOException;
import domain.exceptions.MaisDeUmRegistroException;
import domain.exceptions.TableException;
import domain.exceptions.TipoChaveNaoEncontradaException;
import domain.model.Cliente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

import static junit.framework.TestCase.assertTrue;

public class ClienteDAOTest {

    private IClienteDAO clienteDao;

    public ClienteDAOTest() {
        clienteDao = new ClienteDAO();
    }

    @After
    public void end() throws DAOException {
        Collection<Cliente> list = clienteDao.buscarTodos();
        list.forEach(cli -> {
            try {
                clienteDao.excluir(cli.getCPF());
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    @Test
    public void pesquisarCliente() throws MaisDeUmRegistroException, TableException, TipoChaveNaoEncontradaException, DAOException {
        Cliente cliente = new Cliente();
        cliente.setCPF(12312312312L);
        cliente.setNome("Daniel");
        cliente.setCidade("São Paulo");
        cliente.setEndereco("End");
        cliente.setEstado("SP");
        cliente.setNumero(10);
        cliente.setTelefone(1199999999L);
        clienteDao.cadastrar(cliente);

        Cliente clienteConsultado = clienteDao.consultar(cliente.getCPF());
        Assert.assertNotNull(clienteConsultado);

        clienteDao.excluir(cliente.getCPF());
    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        Cliente cliente = new Cliente();
        cliente.setCPF(56565656565L);
        cliente.setNome("Daniel");
        cliente.setCidade("São Paulo");
        cliente.setEndereco("End");
        cliente.setEstado("SP");
        cliente.setNumero(10);
        cliente.setTelefone(1199999999L);
        Boolean retorno = clienteDao.cadastrar(cliente);
        Assert.assertTrue(retorno);

        Cliente clienteConsultado = clienteDao.consultar(cliente.getCPF());
        Assert.assertNotNull(clienteConsultado);

        clienteDao.excluir(cliente.getCPF());
    }


    @Test
    public void excluirCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        Cliente cliente = new Cliente();
        cliente.setCPF(56565656565L);
        cliente.setNome("Daniel");
        cliente.setCidade("São Paulo");
        cliente.setEndereco("End");
        cliente.setEstado("SP");
        cliente.setNumero(10);
        cliente.setTelefone(1199999999L);
        Boolean retorno = clienteDao.cadastrar(cliente);
        Assert.assertTrue(retorno);

        Cliente clienteConsultado = clienteDao.consultar(cliente.getCPF());
        Assert.assertNotNull(clienteConsultado);

        clienteDao.excluir(cliente.getCPF());
        clienteConsultado = clienteDao.consultar(cliente.getCPF());
        Assert.assertNull(clienteConsultado);
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        Cliente cliente = new Cliente();
        cliente.setCPF(56565656565L);
        cliente.setNome("Daniel");
        cliente.setCidade("São Paulo");
        cliente.setEndereco("End");
        cliente.setEstado("SP");
        cliente.setNumero(10);
        cliente.setTelefone(1199999999L);
        Boolean retorno = clienteDao.cadastrar(cliente);
        Assert.assertTrue(retorno);

        Cliente clienteConsultado = clienteDao.consultar(cliente.getCPF());
        Assert.assertNotNull(clienteConsultado);

        clienteConsultado.setNome("Daniel Pires");
        clienteDao.alterar(clienteConsultado);

        Cliente clienteAlterado = clienteDao.consultar(clienteConsultado.getCPF());
        Assert.assertNotNull(clienteAlterado);
        Assert.assertEquals("Daniel Pires", clienteAlterado.getNome());

        clienteDao.excluir(cliente.getCPF());
        clienteConsultado = clienteDao.consultar(cliente.getCPF());
        Assert.assertNull(clienteConsultado);
    }

    @Test
    public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException {
        Cliente cliente = new Cliente();
        cliente.setCPF(56565656565L);
        cliente.setNome("Daniel");
        cliente.setCidade("São Paulo");
        cliente.setEndereco("End");
        cliente.setEstado("SP");
        cliente.setNumero(10);
        cliente.setTelefone(1199999999L);
        Boolean retorno = clienteDao.cadastrar(cliente);
        Assert.assertTrue(retorno);

        Cliente cliente1 = new Cliente();
        cliente1.setCPF(56565656569L);
        cliente1.setNome("Daniel");
        cliente1.setCidade("São Paulo");
        cliente1.setEndereco("End");
        cliente1.setEstado("SP");
        cliente1.setNumero(10);
        cliente1.setTelefone(1199999999L);
        Boolean retorno1 = clienteDao.cadastrar(cliente1);
        Assert.assertTrue(retorno1);

        Collection<Cliente> list = clienteDao.buscarTodos();
        assertTrue(list != null);
        assertTrue(list.size() == 2);

        list.forEach(cli -> {
            try {
                clienteDao.excluir(cli.getCPF());
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        Collection<Cliente> list1 = clienteDao.buscarTodos();
        assertTrue(list1 != null);
        assertTrue(list1.size() == 0);
    }
}