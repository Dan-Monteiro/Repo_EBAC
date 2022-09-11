package mocks;

import domain.dao.ClienteDAO;
import domain.dao.IClienteDAO;
import domain.exceptions.TipoChaveNaoEncontradaException;
import domain.model.Cliente;

import java.util.Collection;

public class ClienteDAOMock implements IClienteDAO {

    @Override
    public Boolean cadastrar(Cliente entity) throws TipoChaveNaoEncontradaException {
        return true;
    }

    @Override
    public void excluirValor(Long valor) {

    }

    @Override
    public void alterar(Cliente entity) throws TipoChaveNaoEncontradaException {

    }

    @Override
    public Cliente consultar(Long valor) {
        Cliente clienteMock = new Cliente();
        clienteMock.setCPF(99999999999L);
        clienteMock.setNome("Dan");
        clienteMock.setCidade("Cidade");
        clienteMock.setEstado("Estado");
        clienteMock.setEndereco("Endereço");
        clienteMock.setTelefone(218323123L);
        return clienteMock;
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return null;
    }
}
