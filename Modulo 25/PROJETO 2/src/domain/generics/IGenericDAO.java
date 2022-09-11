package domain.generics;

import domain.exceptions.TipoChaveNaoEncontradaException;

import java.io.Serializable;
import java.util.Collection;

public interface IGenericDAO <T extends  Persistente> {

    public Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException;
    public void excluirValor(Long valor);
    public void alterar(T entity) throws TipoChaveNaoEncontradaException;
    public T consultar(Long valor);
    public Collection<T> buscarTodos();
}
