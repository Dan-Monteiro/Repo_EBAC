package br.com.dan.dao;

import br.com.dan.domain.Cliente;
import br.com.dan.generic.GenericDAO;

public class ClientDAOAlt extends GenericDAO<Cliente, Long> implements IClienteDAO {

    public ClientDAOAlt() {
        super(Cliente.class, "ExemploJPATest");
    }

}
