package br.com.dan.dao;

import br.com.dan.Exceptions.DAOException;
import br.com.dan.Exceptions.TipoChaveNaoEncontradaException;
import br.com.dan.domain.Venda;
import br.com.dan.generic.IGenericDAO;

public interface IVendaDAO extends IGenericDAO<Venda, Long>{

	public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;
	
	public void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;

	public Venda consultarComCollection(Long id);
}