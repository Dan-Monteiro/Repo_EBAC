import br.com.daniel.domain.Produto;
import br.com.daniel.generic.jdbc.dao.IProdutoDAO;
import br.com.daniel.generic.jdbc.dao.ProdutoDAO;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ProdutoTest {

    private IProdutoDAO produtoDAO;

    @Test
    public void cadastrarTest() throws Exception {
        // Arrange
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo("10");
        produto.setNome("Coca");
        produto.setValor(8.00);

        // Act
        Integer countCad = produtoDAO.cadastrar(produto);
        Produto produtoBD = (Produto) produtoDAO.buscar("10");
        Integer countDel = produtoDAO.excluir(produtoBD);

        // Assert
        assertEquals(1, (int) countCad);
        assertNotNull(produtoBD);
        assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        assertEquals(produto.getNome(), produtoBD.getNome());
        assertEquals(produto.getValor(), produtoBD.getValor());
        assertEquals(1, (int) countDel);
    }

    @Test
    public void buscarTest() throws Exception {
        // Arrange
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo("10");
        produto.setNome("Suco frutas cíticras");
        produto.setValor(7.00);

        // Act
        Integer countCad = produtoDAO.cadastrar(produto);
        Produto produtoBD = (Produto) produtoDAO.buscar("10");
        produtoDAO.excluir(produtoBD);

        // Assert
        assertEquals(1, (int) countCad);
        assertNotNull(produtoBD);
        assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        assertEquals(produto.getNome(), produtoBD.getNome());
        assertEquals(produto.getValor(), produtoBD.getValor());
    }

    @Test
    public void excluirTest() throws Exception {
        // Arrange
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo("10");
        produto.setNome("Sabão em pó");
        produto.setValor(12.00);

        // Act
        produtoDAO.cadastrar(produto);
        Produto produtoBD = (Produto) produtoDAO.buscar("10");
        Integer countDelete = produtoDAO.excluir(produtoBD);

        // Assert
        assertEquals(1, (int) countDelete);
    }

    @Test
    public void buscarTodos() throws Exception {
        // Arrange
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo("10");
        produto.setNome("Café");
        produto.setValor(8.00);

        Produto produto1 = new Produto();
        produto1.setCodigo("11");
        produto1.setNome("Leite");
        produto1.setValor(7.00);

        List<Produto> listaProdutos;

        AtomicInteger countDel = new AtomicInteger();

        // Act
        Integer countCad = produtoDAO.cadastrar(produto);
        Integer countCad1 = produtoDAO.cadastrar(produto1);

        Produto produtoBD = (Produto) produtoDAO.buscar("10");
        Produto produtoBD1 = (Produto) produtoDAO.buscar("11");

        listaProdutos = produtoDAO.buscarTodos();

        listaProdutos.forEach(item -> {
            try {
                produtoDAO.excluir(item);
                countDel.getAndIncrement();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        // Assert
        assertEquals(1, (int) countCad);
        assertEquals(1, (int) countCad1);
        assertNotNull(produtoBD);
        assertNotNull(produtoBD1);
        assertNotNull(listaProdutos);
        assertEquals(2, listaProdutos.size());
        assertEquals(2, countDel.intValue());
    }

    @Test
    public void atualizarTest() throws Exception {
        // Arrange
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo("10");
        produto.setNome("Arroz");
        produto.setValor(20.00);

        // Act
        Integer countCad = produtoDAO.cadastrar(produto);
        Produto produtoBDOriginal = (Produto) produtoDAO.buscar("10");
        Produto produtoBD = (Produto) produtoDAO.buscar("10");
        produtoBD.setNome("Arroz Integral");
        produtoBD.setCodigo("20");
        produtoBD.setValor(38.00);
        Integer countUpdate = produtoDAO.atualizar(produtoBD);
        Produto produtoAfterUpdate = (Produto) produtoDAO.buscar("10");
        Produto produtoBDAfterUpdate = (Produto) produtoDAO.buscar("20");
        Integer countDel = produtoDAO.excluir(produtoBD);

        // Assert
        assertEquals(1, (int) countCad);
        assertNotNull(produtoBD);
        assertEquals(produto.getCodigo(), produtoBDOriginal.getCodigo());
        assertEquals(produto.getNome(), produtoBDOriginal.getNome());
        assertEquals(produto.getValor(), produtoBDOriginal.getValor());
        assertEquals(1, (int) countUpdate);
        assertNull(produtoAfterUpdate);
        assertNotNull(produtoBDAfterUpdate);
        assertEquals(produtoBDAfterUpdate.getCodigo(), produtoBD.getCodigo());
        assertEquals(produtoBDAfterUpdate.getNome(), produtoBD.getNome());
        assertEquals(produtoBDAfterUpdate.getValor(), produtoBD.getValor());
        assertEquals(1, (int) countDel);
    }
}
