package domain.services;

import domain.dao.IProdutoDAO;
import domain.model.Produto;
import domain.services.generics.GenericService;

public class ProdutoService extends GenericService<Produto, String> implements IProdutoService {

    public ProdutoService(IProdutoDAO dao) {
        super(dao);
    }
}