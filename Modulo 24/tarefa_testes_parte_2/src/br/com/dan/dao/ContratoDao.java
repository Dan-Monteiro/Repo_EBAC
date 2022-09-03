package br.com.dan.dao;

import br.com.dan.Contrato;

public class ContratoDao implements IContratoDao {

    @Override
    public void salvar() {
        throw new UnsupportedOperationException("Não funciona sem o banco");
    }

    @Override
    public Contrato buscar(Long id) {
        throw new UnsupportedOperationException("Não funciona sem o banco");
    }

    @Override
    public boolean excluir(Long id) {
        throw new UnsupportedOperationException("Não funciona sem o banco");
    }

    @Override
    public boolean atualizar(Long codigo) {
        throw new UnsupportedOperationException("Não funciona sem o banco");
    }

}
