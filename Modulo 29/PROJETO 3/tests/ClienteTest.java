import br.com.daniel.domain.Cliente;
import br.com.daniel.generic.jdbc.dao.ClienteDAO;
import br.com.daniel.generic.jdbc.dao.IClienteDAO;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class ClienteTest {

    private IClienteDAO clienteDAO;

    @Test
    public void cadastrarTest() throws Exception {
        // Arrange
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Dan");

        // Act
        Integer countCad = clienteDAO.cadastrar(cliente);
        Cliente clienteBD = (Cliente) clienteDAO.buscar("10");
        Integer countDel = clienteDAO.excluir(clienteBD);

        // Assert
        assertEquals(1, (int) countCad);
        assertNotNull(clienteBD);
        assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        assertEquals(cliente.getNome(), clienteBD.getNome());
        assertEquals(1, (int) countDel);
    }

    @Test
    public void buscarTest() throws Exception {
        // Arrange
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Dan");

        // Act
        Integer countCad = clienteDAO.cadastrar(cliente);
        Cliente clienteBD = (Cliente) clienteDAO.buscar("10");
        clienteDAO.excluir(clienteBD);

        // Assert
        assertEquals(1, (int) countCad);
        assertNotNull(clienteBD);
        assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        assertEquals(cliente.getNome(), clienteBD.getNome());
    }

    @Test
    public void excluirTest() throws Exception {
        // Arrange
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Dan");

        // Act
        clienteDAO.cadastrar(cliente);
        Cliente clienteBD = (Cliente) clienteDAO.buscar("10");
        Integer countDelete = clienteDAO.excluir(clienteBD);

        // Assert
        assertEquals(1, (int) countDelete);
    }

    @Test
    public void buscarTodos() throws Exception {
        // Arrange
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Dan");

        Cliente cliente1 = new Cliente();
        cliente1.setCodigo("11");
        cliente1.setNome("Peter");

        List<Cliente> listaClientes;

        AtomicInteger countDel = new AtomicInteger();

        // Act
        Integer countCad = clienteDAO.cadastrar(cliente);
        Integer countCad1 = clienteDAO.cadastrar(cliente1);

        Cliente clienteBD = (Cliente) clienteDAO.buscar("10");
        Cliente clienteBD1 = (Cliente) clienteDAO.buscar("11");

        listaClientes = clienteDAO.buscarTodos();

        listaClientes.forEach(item -> {
            try {
                clienteDAO.excluir(item);
                countDel.getAndIncrement();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        // Assert
        assertEquals(1, (int) countCad);
        assertEquals(1, (int) countCad1);
        assertNotNull(clienteBD);
        assertNotNull(clienteBD1);
        assertNotNull(listaClientes);
        assertEquals(2, listaClientes.size());
        assertEquals(2, countDel.intValue());
    }

    @Test
    public void atualizarTest() throws Exception {
        // Arrange
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Dan");

        // Act
        Integer countCad = clienteDAO.cadastrar(cliente);
        Cliente clienteBDOriginal = (Cliente) clienteDAO.buscar("10");
        Cliente clienteBD = (Cliente) clienteDAO.buscar("10");
        clienteBD.setNome("John");
        clienteBD.setCodigo("20");
        Integer countUpdate = clienteDAO.atualizar(clienteBD);
        Cliente clienteAfterUpdate = (Cliente) clienteDAO.buscar("10");
        Cliente clienteBDAfterUpdate = (Cliente) clienteDAO.buscar("20");
        Integer countDel = clienteDAO.excluir(clienteBD);

        // Assert
        assertEquals(1, (int) countCad);
        assertNotNull(clienteBD);
        assertEquals(cliente.getCodigo(), clienteBDOriginal.getCodigo());
        assertEquals(cliente.getNome(), clienteBDOriginal.getNome());
        assertEquals(1, (int) countUpdate);
        assertNull(clienteAfterUpdate);
        assertNotNull(clienteBDAfterUpdate);
        assertEquals(clienteBDAfterUpdate.getCodigo(), clienteBD.getCodigo());
        assertEquals(clienteBDAfterUpdate.getNome(), clienteBD.getNome());
        assertEquals(1, (int) countDel);

    }
}
