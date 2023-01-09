package br.com.daniel.generic.jdbc.dao;

import br.com.daniel.domain.Cliente;
import br.com.daniel.generic.jdbc.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO {

    private ResultSet resultSet;
    private Cliente cliente;

    @Override
    public Integer cadastrar(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String SQL = getInsertSql();
            preparedStatement = connection.prepareStatement(SQL);
            adicionarParametrosInsert(preparedStatement, cliente);
            return preparedStatement.executeUpdate();
        } catch (Exception exception) {
            throw  exception;
        } finally {
           closeConnection(connection, preparedStatement, null);
        }
    }

    @Override
    public Integer atualizar(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String SQL = getUpdateSql();
            preparedStatement = connection.prepareStatement(SQL);
            adicionarParametrosUpdate(preparedStatement, cliente);
            return preparedStatement.executeUpdate();
        } catch (Exception exception) {
            throw  exception;
        } finally {
            closeConnection(connection, preparedStatement, null);
        }
    }

    @Override
    public Cliente buscar(String codigo) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String SQL = getSelectSql();
            preparedStatement = connection.prepareStatement(SQL);
            adicionarParametrosSelect(preparedStatement, codigo);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                cliente = new Cliente();
                Long id = resultSet.getLong("ID");
                String nome = resultSet.getString("NOME");
                cliente.setId(id);
                cliente.setCodigo(codigo);
                cliente.setNome(nome);
                return cliente;
            }
            return null;
        } catch (Exception exception) {
            throw  exception;
        } finally {
            closeConnection(connection, preparedStatement, resultSet);
        }
    }


    @Override
    public List<Cliente> buscarTodos() throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String SQL = getSelectAllSql();
            preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();
            List<Cliente> listaClientes = new ArrayList<Cliente>();
            while (resultSet.next()) {
                cliente = new Cliente();
                Long id = resultSet.getLong("ID");
                String nome = resultSet.getString("NOME");
                String codigo = resultSet.getString("CODIGO");
                cliente.setId(id);
                cliente.setCodigo(codigo);
                cliente.setNome(nome);
                listaClientes.add(cliente);
            }
            return listaClientes;
        } catch (Exception exception) {
            throw  exception;
        } finally {
            closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public Integer excluir(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String SQL = getDeleteSql();
            preparedStatement = connection.prepareStatement(SQL);
            adicionarParametrosExcluir(preparedStatement, cliente.getCodigo());
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
        stringBuilder.append("INSERT INTO tb_cliente(id, codigo, nome) ");
        stringBuilder.append("VALUES(nextval('SQ_CLIENTE_ID'), ?, ?);");
        return stringBuilder.toString();
    }

    private String getUpdateSql() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE tb_cliente ");
        stringBuilder.append("SET NOME = ?, CODIGO = ? ");
        stringBuilder.append("WHERE ID = ?;");
        return stringBuilder.toString();
    }

    private String getSelectSql() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM tb_cliente ");
        stringBuilder.append("WHERE CODIGO = ?");
        return stringBuilder.toString();
    }

    private String getSelectAllSql() {
        return "SELECT * FROM tb_cliente";
    }

    private String getDeleteSql() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DELETE FROM tb_cliente ");
        stringBuilder.append("WHERE codigo = ?");
        return stringBuilder.toString();
    }

    private void adicionarParametrosInsert(PreparedStatement preparedStatement, Cliente cliente) throws SQLException {
        preparedStatement.setString(1, cliente.getCodigo());
        preparedStatement.setString(2, cliente.getNome());
    }

    private void adicionarParametrosUpdate(PreparedStatement preparedStatement, Cliente cliente) throws SQLException {
        preparedStatement.setString(1, cliente.getNome());
        preparedStatement.setString(2, cliente.getCodigo());
        preparedStatement.setLong(3, cliente.getId());
    }

    private void adicionarParametrosSelect(PreparedStatement preparedStatement, String codigo) throws SQLException {
        preparedStatement.setString(1, codigo);
    }

    private void adicionarParametrosExcluir(PreparedStatement preparedStatement, String codigo) throws SQLException {
        preparedStatement.setString(1, codigo);
    }
}
