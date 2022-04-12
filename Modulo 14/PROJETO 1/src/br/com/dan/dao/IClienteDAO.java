package br.com.dan.dao;

import br.com.dan.domain.Cliente;

import java.util.Collection;

public interface IClienteDAO {

    public boolean cadastrar(Cliente cliente);
    public void excluir(Long cpf);
    public void atualizar(Cliente cliente);
    public Cliente consultar(Long cpf);
    public Collection<Cliente> buscarTodos();

}
