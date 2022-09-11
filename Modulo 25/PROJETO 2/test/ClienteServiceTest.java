import domain.dao.IClienteDAO;
import domain.exceptions.TipoChaveNaoEncontradaException;
import domain.generics.GenericService;
import domain.model.Cliente;
import domain.services.ClienteService;
import mocks.ClienteDAOMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClienteServiceTest {

    private GenericService<Cliente> clienteService;
    private Cliente cliente;

    public ClienteServiceTest() {
        IClienteDAO clienteDAO = new ClienteDAOMock();
        clienteService = new ClienteService(clienteDAO);
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
        Cliente clienteConsultado = clienteService.buscar(cliente.getCPF());
        Assert.assertNotNull(clienteConsultado);
    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException {
       boolean retorno = clienteService.salvar(cliente);
       Assert.assertTrue(retorno);
    }

    @Test
    public void excluirCliente() {
        clienteService.excluir(cliente.getCPF());
    }

    @Test
    public void atualizarCliente() throws TipoChaveNaoEncontradaException {
        //arrange
        String novoNome = "Dan Monteiro";
        cliente.setNome(novoNome);

        //act
        clienteService.atualizar(cliente);

        //assert
        Assert.assertEquals(novoNome, cliente.getNome());
    }
}
