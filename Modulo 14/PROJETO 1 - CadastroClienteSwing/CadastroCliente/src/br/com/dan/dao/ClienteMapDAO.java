package br.com.dan.dao;

import br.com.dan.domain.Cliente;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ClienteMapDAO implements IClienteDAO {

    private Map<Long, Cliente> clientes;

    public ClienteMapDAO() {
        this.clientes = new HashMap<>();
    }

    @Override
    public boolean cadastrar(Cliente cliente) {
        if (this.clientes.containsKey(cliente.getCpf())) return false;
        this.clientes.put(cliente.getCpf(), cliente);
        return true;
    }

    @Override
    public void excluir(Long cpf) {
        Cliente cliente = this.clientes.get(cpf);
        if(cliente != null) {
            this.clientes.remove(cpf);
        }
    }

    @Override
    public void atualizar(Cliente cliente) {
        Cliente clienteCadastrado = this.clientes.get(cliente.getCpf());
        if(clienteCadastrado != null) {
            clienteCadastrado.atualizaDados(cliente);
        }
    }

    @Override
    public Cliente consultar(Long cpf) {
        return this.clientes.get(cpf);
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return this.clientes.values();
    }
}
