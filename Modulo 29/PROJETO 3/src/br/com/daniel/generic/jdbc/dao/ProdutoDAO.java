package br.com.daniel.generic.jdbc.dao;

import br.com.daniel.domain.Produto;
import br.com.daniel.generic.jdbc.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements IProdutoDAO {

    private ResultSet resultSet;
    private Produto produto;

    @Override
    public Integer cadastrar(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String SQL = getInsertSql();
            preparedStatement = connection.prepareStatement(SQL);
            adicionarParametrosInsert(preparedStatement, produto);
            return preparedStatement.executeUpdate();
        } catch (Exception exception) {
            throw  exception;
        } finally {
            closeConnection(connection, preparedStatement, null);
        }
    }

    @Override
    public Integer atualizar(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String SQL = getUpdateSql();
            preparedStatement = connection.prepareStatement(SQL);
            adicionarParametrosUpdate(preparedStatement, produto);
            return preparedStatement.executeUpdate();
        } catch (Exception exception) {
            throw  exception;
        } finally {
            closeConnection(connection, preparedStatement, null);
        }
    }

    @Override
    public Object buscar(String codigo) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String SQL = getSelectSql();
            preparedStatement = connection.prepareStatement(SQL);
            adicionarParametrosSelect(preparedStatement, codigo);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                produto = new Produto();
                Long id = resultSet.getLong("ID");
                String nome = resultSet.getString("NOME");
                Double valor = resultSet.getDouble("VALOR");
                produto.setId(id);
                produto.setCodigo(codigo);
                produto.setNome(nome);
                produto.setValor(valor);
                return produto;
            }
            return null;
        } catch (Exception exception) {
            throw  exception;
        } finally {
            closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Produto> buscarTodos() throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String SQL = getSelectAllSql();
            preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();
            List<Produto> listaProdutos = new ArrayList<Produto>();
            while (resultSet.next()) {
                produto = new Produto();
                Long id = resultSet.getLong("ID");
                String nome = resultSet.getString("NOME");
                String codigo = resultSet.getString("CODIGO");
                Double valor = resultSet.getDouble("VALOR");
                produto.setId(id);
                produto.setCodigo(codigo);
                produto.setNome(nome);
                produto.setValor(valor);
                listaProdutos.add(produto);
            }
            return listaProdutos;
        } catch (Exception exception) {
            throw  exception;
        } finally {
            closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public Integer excluir(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String SQL = getDeleteSql();
            preparedStatement = connection.prepareStatement(SQL);
            adicionarParametrosExcluir(preparedStatement, produto.getCodigo());
            int rows = preparedStatement.executeUpdate();
            return rows;
        } catch (Exception exception) {
            throw  exception;
        } finally {
            closeConnection(connection, preparedStatement, null);
        }
    }

    private void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null && !resultSet.isClosed()) {
                resultSet.close();
            }
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private String getInsertSql() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO tb_produto(id, codigo, nome, valor) ");
        stringBuilder.append("VALUES(nextval('SQ_PRODUTO_ID'), ?, ?, ?);");
        return stringBuilder.toString();
    }

    private String getUpdateSql() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE tb_produto ");
        stringBuilder.append("SET NOME = ?, CODIGO = ?, valor = ? ");
        stringBuilder.append("WHERE ID = ?;");
        return stringBuilder.toString();
    }

    private String getSelectSql() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM tb_produto ");
        stringBuilder.append("WHERE CODIGO = ?");
        return stringBuilder.toString();
    }

    private String getSelectAllSql() {
        return "SELECT * FROM tb_produto";
    }

    private String getDeleteSql() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DELETE FROM tb_produto ");
        stringBuilder.append("WHERE codigo = ?");
        return stringBuilder.toString();
    }

    private void adicionarParametrosInsert(PreparedStatement preparedStatement, Produto produto) throws SQLException {
        preparedStatement.setString(1, produto.getCodigo());
        preparedStatement.setString(2, produto.getNome());
        preparedStatement.setDouble(3, produto.getValor());
    }

    private void adicionarParametrosUpdate(PreparedStatement preparedStatement,  Produto produto) throws SQLException {
        preparedStatement.setString(1, produto.getNome());
        preparedStatement.setString(2, produto.getCodigo());
        preparedStatement.setDouble(3, produto.getValor());
        preparedStatement.setLong(4, produto.getId());
    }

    private void adicionarParametrosSelect(PreparedStatement preparedStatement, String codigo) throws SQLException {
        preparedStatement.setString(1, codigo);
    }

    private void adicionarParametrosExcluir(PreparedStatement preparedStatement, String codigo) throws SQLException {
        preparedStatement.setString(1, codigo);
    }
}
