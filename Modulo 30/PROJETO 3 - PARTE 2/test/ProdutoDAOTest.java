import domain.dao.EstoqueProdutoDAO;
import domain.dao.IEstoqueProdutoDAO;
import domain.dao.IProdutoDAO;
import domain.dao.ProdutoDAO;
import domain.exceptions.DAOException;
import domain.exceptions.MaisDeUmRegistroException;
import domain.exceptions.TableException;
import domain.exceptions.TipoChaveNaoEncontradaException;
import domain.model.EstoqueProduto;
import domain.model.Produto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;

import static org.junit.Assert.*;

public class ProdutoDAOTest {

    private IProdutoDAO produtoDao;
    private IEstoqueProdutoDAO estoqueProdutoDAO;

    public ProdutoDAOTest() {
        produtoDao = new ProdutoDAO();
        estoqueProdutoDAO = new EstoqueProdutoDAO();
    }

    @After
    public void end() throws DAOException {
        Collection<Produto> list = produtoDao.buscarTodos();
        list.forEach(prod -> {
            try {
                produtoDao.excluir(prod.getCodigo());
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    private Produto criarProduto(String codigo) throws TipoChaveNaoEncontradaException, DAOException {
        Produto produto = new Produto();
        produto.setCodigo(codigo);
        produto.setDescricao("Produto 1");
        produto.setNome("Produto 1");
        produto.setValor(BigDecimal.TEN);
        produto.setMarca("Marca");
        produto.setQuantidadeInicial(10);
        produtoDao.cadastrar(produto);
        return produto;
    }

    @Test
    public void criaProdutoComQuantidadeInicialEstoqueProduto() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
        Produto produto = criarProduto("Codigo 1");
        Produto produtoBD = produtoDao.consultar(produto.getCodigo());
        EstoqueProduto estoqueProduto = estoqueProdutoDAO.consultar(produtoBD.getId());

        assertNotNull(produtoBD);
        assertNotNull(estoqueProduto);
        assertEquals(10, (int) estoqueProduto.getQuantidade());
    }

    private void excluir(String valor) throws DAOException {
        this.produtoDao.excluir(valor);
    }

    @Test
    public void pesquisar() throws MaisDeUmRegistroException, TableException, DAOException, TipoChaveNaoEncontradaException {
        Produto produto = criarProduto("A1");
        assertNotNull(produto);
        Produto produtoDB = this.produtoDao.consultar(produto.getCodigo());
        assertNotNull(produtoDB);
        //excluir(produtoDB.getCodigo());
    }

    @Test
    public void salvar() throws TipoChaveNaoEncontradaException, DAOException {
        Produto produto = criarProduto("A2");
        assertNotNull(produto);
        excluir(produto.getCodigo());
    }

    @Test
    public void excluir() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {
        Produto produto = criarProduto("A3");
        assertNotNull(produto);
        excluir(produto.getCodigo());
        Produto produtoBD = this.produtoDao.consultar(produto.getCodigo());
        assertNull(produtoBD);
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
        Produto produto = criarProduto("A4");
        produto.setNome("Rodrigo Pires");
        produtoDao.alterar(produto);
        Produto produtoBD = this.produtoDao.consultar(produto.getCodigo());
        assertNotNull(produtoBD);
        Assert.assertEquals("Rodrigo Pires", produtoBD.getNome());

        excluir(produto.getCodigo());
        Produto produtoBD1 = this.produtoDao.consultar(produto.getCodigo());
        assertNull(produtoBD1);
    }

    @Test
    public void buscarTodos() throws DAOException, TipoChaveNaoEncontradaException {
        criarProduto("A5");
        criarProduto("A6");
        Collection<Produto> list = produtoDao.buscarTodos();
        assertTrue(list != null);
        assertTrue(list.size() == 2);

        for (Produto prod : list) {
            excluir(prod.getCodigo());
        }

        list = produtoDao.buscarTodos();
        assertTrue(list != null);
        assertTrue(list.size() == 0);
    }
}