package br.com.daniel.generic.jdbc.dao;

import br.com.daniel.domain.Cliente;

import java.util.List;

public interface IClienteDAO {
    public Integer cadastrar(Cliente cliente) throws Exception;
    public Integer atualizar(Cliente cliente) throws Exception;
    public Object buscar(String codigo) throws Exception;
    public List<Cliente> buscarTodos() throws Exception;
    public Integer excluir(Cliente cliente) throws Exception;
}
