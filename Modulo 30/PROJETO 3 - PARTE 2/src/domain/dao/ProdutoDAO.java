package domain.dao;

import domain.exceptions.DAOException;
import domain.exceptions.MaisDeUmRegistroException;
import domain.exceptions.TableException;
import domain.exceptions.TipoChaveNaoEncontradaException;
import domain.generics.GenericDAO;
import domain.model.EstoqueProduto;
import domain.model.Produto;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoDAO extends GenericDAO<Produto, String> implements IProdutoDAO {

    public ProdutoDAO() {
        super();
    }

    @Override
    public Class<Produto> getTipoClasse() {
        return Produto.class;
    }

    @Override
    public Boolean cadastrar(Produto entity) throws TipoChaveNaoEncontradaException, DAOException {
        boolean result = super.cadastrar(entity);
        IEstoqueProdutoDAO estoqueProdutoDAO;
        if(result) {
            try {
                EstoqueProduto estoqueProduto = new EstoqueProduto();
                estoqueProdutoDAO = new EstoqueProdutoDAO();
                Produto produto = this.consultar(entity.getCodigo());
                produto.setQuantidadeInicial(entity.getQuantidadeInicial());
                estoqueProduto.populateEstoqueProduto(produto);
                boolean resultEstoque = estoqueProdutoDAO.cadastrar(estoqueProduto);
                return resultEstoque;
            } catch (MaisDeUmRegistroException | TableException e) {
                throw new DAOException("ERRO CADASTRANDO OBJETO ", e);
            }
        }
        return false;
    }

    @Override
    public void atualizarDados(Produto entity, Produto entityCadastrado) {
        entityCadastrado.setCodigo(entity.getCodigo());
        entityCadastrado.setDescricao(entity.getDescricao());
        entityCadastrado.setNome(entity.getNome());
        entityCadastrado.setValor(entity.getValor());
    }

    @Override
    protected String getQueryInsercao() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO TB_PRODUTO ");
        sb.append("(ID, CODIGO, NOME, DESCRICAO, VALOR, MARCA)");
        sb.append("VALUES (nextval('sq_produto'),?,?,?,?,?)");
        return sb.toString();
    }

    @Override
    protected String getQueryExclusao() {
        return "DELETE FROM TB_PRODUTO WHERE CODIGO = ?";
    }

    @Override
    protected void setParametrosQueryExclusao(PreparedStatement stmExclusao, String valor) throws SQLException {
        stmExclusao.setString(1, valor);
    }

    @Override
    protected String getQueryAtualizacao() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE TB_PRODUTO ");
        sb.append("SET CODIGO = ?,");
        sb.append("NOME = ?,");
        sb.append("DESCRICAO = ?,");
        sb.append("VALOR = ?,");
        sb.append("MARCA = ?");
        sb.append(" WHERE CODIGO = ?");
        return sb.toString();
    }

    @Override
    protected void setParametrosQueryInsercao(PreparedStatement stmInsert, Produto entity) throws SQLException {
        stmInsert.setString(1, entity.getCodigo());
        stmInsert.setString(2, entity.getNome());
        stmInsert.setString(3, entity.getDescricao());
        stmInsert.setBigDecimal(4, entity.getValor());
        stmInsert.setString(5, entity.getMarca());
    }

    @Override
    protected void setParametrosQueryAtualizacao(PreparedStatement stmUpdate, Produto entity) throws SQLException {
        stmUpdate.setString(1, entity.getCodigo());
        stmUpdate.setString(2, entity.getNome());
        stmUpdate.setString(3, entity.getDescricao());
        stmUpdate.setBigDecimal(4, entity.getValor());
        stmUpdate.setString(5, entity.getMarca());
        stmUpdate.setString(6, entity.getCodigo());
    }

    @Override
    protected void setParametrosQuerySelect(PreparedStatement stmExclusao, String valor) throws SQLException {
        stmExclusao.setString(1, valor);
    }
}