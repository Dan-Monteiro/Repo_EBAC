package domain.generics;

import domain.exceptions.TipoChaveNaoEncontradaException;

import java.util.Collection;

public abstract class GenericService<T extends Persistente> implements IGenericService<T> {

    protected IGenericDAO<T> dao;

    public GenericService(IGenericDAO<T> dao){
        this.dao = dao;
    }

    @Override
    public boolean salvar(T entity) throws TipoChaveNaoEncontradaException {
        return dao.cadastrar(entity);
    }

    @Override
    public T buscar(Long valor) {
        return dao.consultar(valor);
    }

    @Override
    public void excluir(Long valor) {
        dao.excluirValor(valor);
    }

    @Override
    public void atualizar(T entity) throws TipoChaveNaoEncontradaException {
        dao.alterar(entity);
    }

    @Override
    public Collection<T> buscarTodos() {
        return dao.buscarTodos();
    }
}
