package domain.generics;

import domain.exceptions.TipoChaveNaoEncontradaException;
import domain.generics.Persistente;

import java.util.Collection;

public interface IGenericService<T extends Persistente> {
    boolean salvar(T entity) throws TipoChaveNaoEncontradaException;
    T buscar(Long valor);
    void excluir(Long valor);
    void atualizar(T entity) throws TipoChaveNaoEncontradaException;
    Collection<T> buscarTodos();
}
