package domain.dao;

import domain.generics.GenericDAO;
import domain.model.Cliente;

public class ClienteDAO extends GenericDAO<Cliente> {

    @Override
    public Class<Cliente> getTipoClasse() {
        return Cliente.class;
    }

    @Override
    public void atualiarDados(Cliente entity, Cliente entityCadastrado) {
        // Lógica de atualização dos dados
    }
}
