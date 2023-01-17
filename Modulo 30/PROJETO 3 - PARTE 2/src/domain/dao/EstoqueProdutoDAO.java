package domain.dao;

import domain.generics.GenericDAO;
import domain.model.EstoqueProduto;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EstoqueProdutoDAO extends GenericDAO <EstoqueProduto, Long> implements IEstoqueProdutoDAO {

    @Override
    public Class<EstoqueProduto> getTipoClasse() {
        return EstoqueProduto.class;
    }

    @Override
    public void atualizarDados(EstoqueProduto entity, EstoqueProduto entityCadastrado) {
        entityCadastrado.setQuantidade(entity.getQuantidade());
    }

    @Override
    protected String getQueryInsercao() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO TB_ESTOQUE_PRODUTO");
        sb.append("(id, id_produto_fk, quantidade) VALUES (nextval('sq_estoque_produto'),?,?);");
        return sb.toString();
    }

    @Override
    protected String getQueryExclusao() {
        throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
    }

    @Override
    protected String getQueryAtualizacao() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE TB_ESTOQUE_PRODUTO ");
        sb.append("SET quantidade = ? ");
        sb.append("WHERE id_produto_fk = ?");
        return sb.toString();
    }

    @Override
    protected void setParametrosQueryInsercao(PreparedStatement stmInsert, EstoqueProduto entity) throws SQLException {
        stmInsert.setLong(1, entity.getIdProdutoFk());
        stmInsert.setLong(2, entity.getQuantidade());
    }

    @Override
    protected void setParametrosQueryExclusao(PreparedStatement stmDelete, Long valor) throws SQLException {
        throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
    }

    @Override
    protected void setParametrosQueryAtualizacao(PreparedStatement stmUpdate, EstoqueProduto entity) throws SQLException {
        stmUpdate.setLong(1, entity.getQuantidade());
        stmUpdate.setLong(2, entity.getIdProdutoFk());
    }

    @Override
    protected void setParametrosQuerySelect(PreparedStatement stmGet, Long valor) throws SQLException {
        stmGet.setLong(1, valor);
    }
}
