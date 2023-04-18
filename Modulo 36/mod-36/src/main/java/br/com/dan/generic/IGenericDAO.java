package br.com.dan.generic;

import java.io.Serializable;
import java.util.Collection;

import br.com.dan.Exceptions.DAOException;
import br.com.dan.Exceptions.MaisDeUmRegistroException;
import br.com.dan.Exceptions.TableException;
import br.com.dan.Exceptions.TipoChaveNaoEncontradaException;
import br.com.dan.dao.Persistente;

public interface IGenericDAO <T extends Persistente, E extends Serializable> {

    public T cadastrar(T entity) throws TipoChaveNaoEncontradaException, DAOException;

    public void excluir(T entity) throws DAOException;

    public T alterar(T entity) throws TipoChaveNaoEncontradaException, DAOException;

    public T consultar(E id) throws MaisDeUmRegistroException, TableException, DAOException;

    public Collection<T> buscarTodos() throws DAOException;

    public String getScopeDB();
}