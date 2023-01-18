package domain.dao.factory;

import domain.model.Cliente;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteFactory {

    public static Cliente convert(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getLong("ID_CLIENTE"));
        cliente.setNome(rs.getString(("NOME")));
        cliente.setCPF(rs.getLong(("CPF")));
        cliente.setTelefone(rs.getLong(("TELEFONE")));
        cliente.setEndereco(rs.getString(("ENDERECO")));
        cliente.setNumero(rs.getInt(("NUMERO")));
        cliente.setCidade(rs.getString(("CIDADE")));
        cliente.setEstado(rs.getString(("ESTADO")));
        cliente.setDataCadastro(rs.getTimestamp("DATA_CADASTRO").toInstant());
        return cliente;
    }
}
