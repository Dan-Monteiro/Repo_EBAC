package domain.services;

import domain.dao.IClienteDAO;
import domain.generics.GenericService;
import domain.model.Cliente;

public class ClienteService extends GenericService<Cliente> {

    public ClienteService(IClienteDAO dao) {
        super(dao);
    }

}
