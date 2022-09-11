import domain.dao.IClienteDAO;
import domain.exceptions.TipoChaveNaoEncontradaException;
import domain.model.Cliente;
import mocks.ClienteDAOMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClienteDAOTest {

    private IClienteDAO clienteDAO;
    private Cliente cliente;

    public ClienteDAOTest() {
        clienteDAO = new ClienteDAOMock();
    }

    @Before
    public void init() {
        cliente = new Cliente();
        cliente.setCPF(99999999999L);
        cliente.setNome("Dan");
        cliente.setCidade("Cidade");
        cliente.setEstado("Estado");
        cliente.setEndereco("Endereço");
        cliente.setTelefone(218323123L);
    }

    @Test
    public void pesquisarCliente() {
        Cliente clienteConsultado = clienteDAO.consultar(cliente.getCPF());
        Assert.assertNotNull(clienteConsultado);
    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException {
        boolean resultado = clienteDAO.cadastrar(cliente);
        Assert.assertTrue(resultado);
    }

    @Test
    public void atualizarCliente() throws TipoChaveNaoEncontradaException {
        //arrange
        String novoNome = "Dan Monteiro";
        cliente.setNome(novoNome);

        //act
        clienteDAO.alterar(cliente);

        //assert
        Assert.assertEquals(novoNome, cliente.getNome());
    }
}
