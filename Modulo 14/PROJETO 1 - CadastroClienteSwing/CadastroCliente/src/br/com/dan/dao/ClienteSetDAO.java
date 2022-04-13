package br.com.dan.dao;

import br.com.dan.domain.Cliente;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class ClienteSetDAO implements IClienteDAO {

    private Set<Cliente> clientes;

    public ClienteSetDAO() {
        clientes = new TreeSet<>();
    }

    @Override
    public boolean cadastrar(Cliente cliente) {
        clientes.add(cliente);
        return true;
    }

    @Override
    public void excluir(Long cpf) {
        for (Cliente clienteCadastrado: clientes) {
            if (clienteCadastrado.getCpf().equals(cpf)) {
                clientes.remove(clienteCadastrado);
                break;
            }
        }
    }

    @Override
    public void atualizar(Cliente cliente) {
        for (Cliente clienteCadastrado: clientes) {
            if (clienteCadastrado.equals(cliente)) {
                clienteCadastrado.atualizaDados(cliente);
                break;
            }
        }
    }

    @Override
    public Cliente consultar(Long cpf) {
        for (Cliente cliente: clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return clientes;
    }
}
