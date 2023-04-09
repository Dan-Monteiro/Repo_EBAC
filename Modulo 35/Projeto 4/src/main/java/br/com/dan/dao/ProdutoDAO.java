package br.com.dan.dao;

import br.com.dan.domain.Produto;
import br.com.dan.generic.GenericDAO;

public class ProdutoDAO extends GenericDAO<Produto, Long> implements IProdutoDAO {

	public ProdutoDAO() {
		super(Produto.class);
	}

}