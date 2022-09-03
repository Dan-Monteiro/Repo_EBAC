package br.com.dan.dao.mocks;

import br.com.dan.Contrato;
import br.com.dan.dao.IContratoDao;

public class ContratoDaoMock implements IContratoDao {

    @Override
    public void salvar() {

    }

    @Override
    public Contrato buscar(Long id) {
        return new Contrato();
    }

    @Override
    public boolean excluir(Long id) {
        return true;
    }

    @Override
    public boolean atualizar(Long codigo) {
        return true;
    }
}
