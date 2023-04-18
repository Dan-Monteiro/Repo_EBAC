package br.com.dan.dao;

import br.com.dan.domain.Cliente;
import br.com.dan.generic.GenericDAO;

public class ClienteDAO extends GenericDAO<Cliente, Long> implements IClienteDAO {

	public ClienteDAO() {
		super(Cliente.class);
	}

}
