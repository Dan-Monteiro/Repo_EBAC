package test.br.com.dan;

import br.com.dan.Contrato;
import br.com.dan.dao.ContratoDao;
import br.com.dan.dao.IContratoDao;
import br.com.dan.dao.mocks.ContratoDaoMock;
import br.com.dan.service.ContratoService;
import br.com.dan.service.IContratoService;
import org.junit.Assert;
import org.junit.Test;

public class ContratoServiceTest {

    @Test
    public void salvarTest() {
        IContratoDao dao = new ContratoDaoMock();
        IContratoService service = new ContratoService(dao);
        String retorno = service.salvar();
        Assert.assertEquals("Sucesso", retorno);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void esperadoErroNoSalvarComBancoDeDadosTest() {
        IContratoDao dao = new ContratoDao();
        IContratoService service = new ContratoService(dao);
        String retorno = service.salvar();
        Assert.assertEquals("Sucesso", retorno);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void esperadoErroBuscarContrato() {
        IContratoDao dao = new ContratoDao();
        IContratoService service = new ContratoService(dao);
        Contrato contrato = new Contrato();
        Contrato contratoBusca = service.buscar(contrato.codigo);
        Assert.assertNotEquals(null, contratoBusca);
    }

    @Test
    public void buscarContratoTeste() {
        IContratoDao dao = new ContratoDaoMock();
        IContratoService service = new ContratoService(dao);
        Contrato contrato = new Contrato();
        Contrato contratoBusca = service.buscar(contrato.codigo);
        Assert.assertNotEquals(null, contratoBusca);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void esperadoErroExcluirContrato() {
        IContratoDao dao = new ContratoDao();
        IContratoService service = new ContratoService(dao);
        Contrato contrato = new Contrato();
        boolean result = service.excluir(contrato.codigo);
        Assert.assertEquals(true, result);
    }

    @Test
    public void excluirContratoTeste() {
        IContratoDao dao = new ContratoDaoMock();
        IContratoService service = new ContratoService(dao);
        Contrato contrato = new Contrato();
        boolean result = service.excluir(contrato.codigo);
        Assert.assertEquals(true, result);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void esperadoErroAtualizarContrato() {
        IContratoDao dao = new ContratoDao();
        IContratoService service = new ContratoService(dao);
        Contrato contrato = new Contrato();
        boolean result = service.atualizar(contrato.codigo);
        Assert.assertEquals(true, result);
    }

    @Test
    public void atualizarContratoTeste() {
        IContratoDao dao = new ContratoDaoMock();
        IContratoService service = new ContratoService(dao);
        Contrato contrato = new Contrato();
        boolean result = service.atualizar(contrato.codigo);
        Assert.assertEquals(true, result);
    }

}
