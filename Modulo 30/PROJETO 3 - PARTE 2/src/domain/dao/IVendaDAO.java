package domain.dao;

import domain.exceptions.DAOException;
import domain.exceptions.TipoChaveNaoEncontradaException;
import domain.generics.IGenericDAO;
import domain.model.Venda;

public interface IVendaDAO extends IGenericDAO<Venda, String> {

    public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;
    public void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;
}
